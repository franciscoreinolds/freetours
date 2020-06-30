package backendApplication.controller;

import backendApplication.controller.expeptions.NotFoundException;
import backendApplication.model.dao.*;
import backendApplication.model.entities.*;
import backendApplication.viewmodel.RegisterScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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


    @RequestMapping(value = "/createTour", method = RequestMethod.POST)
    public Integer createTour(@RequestBody Tour tour) {
        try{
            // Save tour
            tourService.save(tour);

            // Save tour on user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);
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
            // Get tour
            Tour tour = tourService.get(Integer.parseInt(id));

            for (Scheduling s : schedulings){
                // Associate tour and save scheduling
                s.setPaid(true);
                s.setTour(tour);
                schedulingService.save(s);

                // Save scheduling on user
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userService.get(username);
                user.addScheduling(s);
                userService.save(user);

                // Save scheduling active on tour
                tour.addActive(s);
                tourService.save(tour);
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
            Tour tour = tourService.get(Integer.parseInt(id));

            // Recursion problem
            tour.getCity().setTours(null);
            for (Scheduling s : tour.getActive()) {
                s.setTour(null);
                List<User> signees = s.getSignees();
                for (User u : signees){
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
            if(!username.equals(tour.getGuideUsername())) {

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
                    // Save tour and scheduling on user
                    user.addTour(tour);
                    user.addScheduling(register);
                    userService.save(user);

                    // Add user to signee list
                    for(int i=0; i<s.getNrPeople(); i++)
                        register.addSignee(user);
                }
                else {
                    // Add user to waiting queue
                    for(int i=0; i<s.getNrPeople(); i++)
                        register.addQueue(user);
                }

                // Save user on scheduling
                schedulingService.save(register);
            }

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
            for (Tour tour : tours) {
                tour.getCity().setTours(null);
                for (Scheduling s : tour.getActive()) {
                    s.setTour(null);
                    List<User> signees = s.getSignees();
                    for (User u : signees){
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

}
