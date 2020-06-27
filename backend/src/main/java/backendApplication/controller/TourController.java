package backendApplication.controller;

import backendApplication.controller.expeptions.NotFoundException;
import backendApplication.model.dao.*;
import backendApplication.model.entities.*;
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


    @RequestMapping(value = "/createTour", method = RequestMethod.POST)
    public Integer createTour(@RequestBody Tour tour) {
        try{
            tourService.save(tour);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);
            user.addTour(tour);
            userService.save(user);
        }catch (Exception ex) {
            return -1;
        }
        return tour.getId();
    }

    @RequestMapping(value = "/createScheduling/{idTour}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createSchedulings(@PathVariable(value="idTour") String id, @RequestBody List<Scheduling> schedulings) {
        try{
            Tour tour = tourService.get(Integer.parseInt(id));
            for (Scheduling s : schedulings){
                s.setPaid(false);
                s.setTour(tour);
                schedulingService.save(s);
            }
        }catch (Exception ex) {
            return new ResponseEntity<> (HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<> (HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tour/{idTour}", method = RequestMethod.GET)
    public Tour getTour(@PathVariable(value="idTour") String id) {
        try{
            return tourService.get(Integer.parseInt(id));
        }catch (NoSuchElementException e) {
            throw new NotFoundException();
        }
    }

    // ------- POR FAZER --------

    @RequestMapping(value = "/tour/{idTour}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerScheduling(@PathVariable(value="idTour") String id, @RequestBody Scheduling scheduling) {
        // falta nrPeople
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.get(username);

            Tour tour = tourService.get(Integer.parseInt(id)); // dava para ir buscar pelo scheduling
            int maxCapacity = tour.getMaxCapacity();

            if (scheduling.getSignees().size() < maxCapacity) {
                scheduling.addSignee(user);
                user.addTour(tour);
                user.addScheduling(scheduling);
            } else scheduling.addQueue(user);

            //userService.save(user);
            //schedulingService.save(scheduling);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<> (HttpStatus.CREATED);
    }

}
