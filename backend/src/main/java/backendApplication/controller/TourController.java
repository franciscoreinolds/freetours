package backendApplication.controller;

import backendApplication.controller.expeptions.NotFoundException;
import backendApplication.model.SwapManager;
import backendApplication.model.dao.*;
import backendApplication.model.entities.*;
import backendApplication.viewmodel.RegisterScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


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
    public Tour getTour(@PathVariable(value="idTour") String id) {
        try{
            // Get tour
            Tour t = tourService.get(Integer.parseInt(id));

            // Recursion problem
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

            return tour;
        }catch (NoSuchElementException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/tour/{idTour}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerScheduling(@PathVariable(value="idTour") String id, @RequestBody RegisterScheduling s) {

        Scheduling register = null;
        try {
            // Get user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            // Get tour
            Tour tour = tourService.get(Integer.parseInt(id));

            // If not the tour guide ...
            if(username.equals(tour.getGuide().getUsername()))
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            // Get active schedulings on tour
            List<Scheduling> schedulings = tour.getActive();

            // Get selected scheduling
            for (Scheduling scheduling : schedulings){
                if(s.getDate().equals(scheduling.getDate()))
                    register = scheduling;
                break;
            }

            // Check capacity
            int maxCapacity = tour.getMaxCapacity();

            if (register.getSignees().size() + s.getNrPeople() < maxCapacity) {
                // Save scheduling on user
                user.addScheduling(register);
                userService.save(user);

                // Add user to signee list
                for (int i = 0; i < s.getNrPeople(); i++)
                    register.addSignee(user);
            }
            else {
                // Add user to waiting queue
                for(int i=0; i<s.getNrPeople(); i++)
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

    @RequestMapping(value = "/tour/{idTour}/unsubscribe", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> unsubscribeScheduling(@PathVariable(value="idTour") String id, @RequestBody RegisterScheduling s) {

        Scheduling register = null;
        try {
            // Get user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            // Get tour
            Tour tour = tourService.get(Integer.parseInt(id));

            // Get active schedulings on tour
            List<Scheduling> schedulings = tour.getActive();

            // Get selected scheduling
            for (Scheduling scheduling : schedulings) {
                if (s.getDate().equals(scheduling.getDate()))
                    register = scheduling;
                break;
            }

            // If less than 24 hours to tour ...
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime schedulingMoment = register.getDate();
            Duration duration = Duration.between(now, schedulingMoment);
            long diff = Math.abs(duration.toMinutes());
            if (diff < 24 * 60) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            // If is the tour guide ...
            if(username.equals(tour.getGuide().getUsername())){

                // Send email to tourists
                // ...

                // Delete scheduling
                tour.removeActive(register);
                tourService.save(tour);
                schedulingService.delete(register.getId());

            }else{ // If is a tourist ...

                // Remove scheduling from user
                user.removeScheduling(register);
                userService.save(user);

                // Remove user/users from signee list
                for (int i = 0; i < s.getNrPeople(); i++)
                    register.removeSignee(user);

                 // If exists users in queue ...
                if(!register.getQueue().isEmpty()) {

                    // New user/users in waiting queue to signee list
                    List<User> newSignees = register.getQueue().subList(0, s.getNrPeople());

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
