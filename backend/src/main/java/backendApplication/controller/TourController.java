package backendApplication.controller;

import backendApplication.controller.expeptions.NotFoundException;
import backendApplication.model.SwapManager;
import backendApplication.model.dao.*;
import backendApplication.model.emailBuilder.Email;
import backendApplication.model.emailBuilder.EmailDirector;
import backendApplication.model.emailBuilder.SchedulingCancellation;
import backendApplication.model.entities.*;
import backendApplication.model.mailer.MailerContext;
import backendApplication.viewmodel.RegisterScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.stream.Collectors;



@RestController
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    UserService userService;

    @Autowired
    SchedulingService schedulingService;

    @Autowired
    CityService cityService;

    @Autowired
    SwapManager swapManager;

    @Autowired
    private MailerContext mailerContext;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/createTour", method = RequestMethod.POST)
    public Integer createTour(@RequestBody Tour tour) {
        try{
            // Get user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            // Check if user is a guide ...
            if (!user.allParametersFilled()) return -2;

            // Save tour
            tourService.save(tour);

            // Save tour on user
            user.addTour(tour);
            userService.save(user);

            // Save tour on city
            City city = cityService.get(tour.getCity().getId());
            city.addTour(tour);
            cityService.save(city);
        }catch (Exception ex) {
            return -1;
        }
        return tour.getId();
    }

    @RequestMapping(value = "/createScheduling/{idTour}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createSchedulings(@PathVariable(value="idTour") String id, @RequestBody List<Scheduling> schedulings) {
        try{
            // Get user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            // Check if user is a guide ...
            if (!user.allParametersFilled()) return new ResponseEntity<> (HttpStatus.NOT_ACCEPTABLE);

            // Get tour
            Tour tour = tourService.get(Integer.parseInt(id));
            System.out.println(Arrays.toString(schedulings.toArray()));
            for (Scheduling s : schedulings){
                // Associate tour and save scheduling
                s.setTour(tour);
                schedulingService.save(s);

                // Save scheduling active on tour
                tour.addActive(s);
                tourService.save(tour);

                // Add schedule to swap manager
                System.out.println("Adicionou schedule");
                swapManager.addSchedule(s);
            }
        }catch (Exception ex) {
            return new ResponseEntity<> (HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<> (HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tour/{idTour}", method = RequestMethod.GET)
    public Map<String, Object> getTour(@PathVariable(value="idTour") String id) {
        try{
            Map<String, Object> r = new HashMap<>();

            // Get tour
            Tour t = tourService.get(Integer.parseInt(id));

            // Recursion problem
            Tour tour = (Tour) t.clone();
            List<Tour> moreToursBy = tour.getGuide().getTours()
                    .stream().filter(t1 -> t1.getActive().size() != 0 )
                    .map(t1 -> {
                        t1 = (Tour) t1.clone();
                        t1.setFinished(null);
                        t1.setActive(null);
                        t1.setCategory(null);
                        t1.setCity(null);
                        t1.setLanguages(null);
                        t1.setReviews(null);
                        t1.setLanguages(null);
                        t1.setGuideUsername(null);
                        t1.setRoute(null);
                        return t1;
                    })
                    .collect(Collectors.toList());
            tour.getCity().setTours(null);
            tour.getGuide().setSchedules(null);
            tour.getGuide().setTours(null);
            for (Scheduling s : tour.getActive()) {
                s.setTour(null);
                for (User u : s.getSignees()){
                    u.setSchedules(null);
                    u.setTours(null);
                }
            }


            r.put("tour", tour);
            r.put("moreToursBy", moreToursBy);

            return r;
        }catch (NoSuchElementException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/schedule_signin", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerScheduling(@RequestBody RegisterScheduling registerScheduling) {

        Scheduling register = null;
        try {
            // Get user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            // Get selected scheduling
            register = schedulingService.get(registerScheduling.getScheduleId());

            // Get tour
            Tour tour = register.getTour();

            // If not the tour guide ...
            if(username.equals(tour.getGuide().getUsername()))
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            // Check capacity
            int maxCapacity = tour.getMaxCapacity();

            if (register.getSignees().size() + registerScheduling.getNrPeople() < maxCapacity) {
                // Save scheduling on user
                user.addScheduling(register);
                userService.save(user);

                // Add user to signee list
                for (int i = 0; i < registerScheduling.getNrPeople(); i++)
                    register.addSignee(user);
            }
            else {
                // Add user to waiting queue
                for(int i=0; i< registerScheduling.getNrPeople(); i++)
                    register.addQueue(user);
            }

            // Save scheduling
            schedulingService.save(register);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<> (HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search/{destination}", method = RequestMethod.GET)
    public List<Tour> getDestinationTours(@PathVariable(value="destination") String destination) {
        try{
            // Get city
            City city = cityService.getByName(destination);

            // Recursion problem
            List<Tour> tours = city.getTours();
            for (Tour t : tours) {
                Tour tour = (Tour) t.clone();
                tour.getCity().setTours(null);
                tour.getGuide().setSchedules(null);
                tour.getGuide().setTours(null);
                for (Scheduling s : tour.getActive()) {
                    s.setTour(null);
                    for (User u : s.getSignees()){
                        u.setSchedules(null);
                        u.setTours(null);
                    }
                }
            }

            return tours;
        }catch (NoSuchElementException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/schedule/unsubscribe", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> unsubscribeScheduling(RegisterScheduling registerScheduling) {

        Scheduling register = null;
        try {
            // Get user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            // Get selected scheduling
            register = schedulingService.get(registerScheduling.getScheduleId());


            // Get tour
            Tour tour = register.getTour();


            // If less than 24 hours to tour ...
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime schedulingMoment = register.getDate();
            Duration duration = Duration.between(now, schedulingMoment);
            long diff = Math.abs(duration.toMinutes());
            if (diff < 24 * 60) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            // If is the tour guide ...
            if(username.equals(tour.getGuide().getUsername())){

                // notify signees by email
                String emailText = tour.getName() + " at " + register.getDate().toString();
                for(User signee : register.getSignees()){
                    EmailDirector builder = new EmailDirector(new SchedulingCancellation());
                    Email email = builder.createEmail(env.getProperty("app.email"), signee.getEmail(), null, emailText);
                    mailerContext.send(email);
                }

                // Delete scheduling
                tour.removeActive(register);
                tourService.save(tour);
                schedulingService.delete(register.getId());

            }else{ // If is a tourist ...

                // Remove scheduling from user
                user.removeScheduling(register);
                userService.save(user);

                // Remove user/users from signee list
                for (int i = 0; i < registerScheduling.getNrPeople(); i++)
                    register.removeSignee(user);

                 // If exists users in queue ...
                if(!register.getQueue().isEmpty()) {

                    // New user/users in waiting queue to signee list
                    List<User> newSignees = register.getQueue().subList(0, registerScheduling.getNrPeople());

                    // Add user/users to signee list
                    for (User u : newSignees) {
                        // Save tour and scheduling on new user
                        u.addTour(tour);
                        u.addScheduling(register);
                        userService.save(u);

                        // Add new user to signee list
                        register.addSignee(u);
                    }
                }

                // Save new scheduling
                schedulingService.save(register);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<> (HttpStatus.CREATED);
    }

}
