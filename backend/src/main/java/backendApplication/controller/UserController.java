package backendApplication.controller;

import backendApplication.model.dao.UserService;
import backendApplication.model.entities.City;
import backendApplication.model.entities.Scheduling;
import backendApplication.model.entities.Tour;
import backendApplication.model.entities.User;
import backendApplication.viewmodel.ProfileView;
import backendApplication.viewmodel.ProfileViewAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    * Get method for getting a user profile
    *
    * @param username String that represents the username which is a PathVariable
    *
    * @return ProfileView that represents only what is intended to show
    * */
    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> profile(@PathVariable String username) {

        try {

            User u = userService.get(username);
            User user = recursionTreatment(u);

            String jwt_username = SecurityContextHolder.getContext().getAuthentication().getName();

            return jwt_username.equals(username)
                    ? ResponseEntity.ok(new ProfileViewAll(user))
                    : ResponseEntity.ok(new ProfileView(user));

        } catch (NoSuchElementException e){

            return new ResponseEntity<>("User doesn't exist.", HttpStatus.NOT_FOUND);

        }
    }

    /*
     * POST method for editing a user profile
     *
     * @param username String that represents the username which is a PathVariable
     * @param user User that represents the user fields to change
     *
     * @return String and HTTP status for informing about the status of the operation
     * */
    @RequestMapping(value = "/profile/{username}", method = RequestMethod.POST)
    public ResponseEntity<String> editProfile(@PathVariable String username, @RequestBody User user) {

        User u = userService.get(username);

        if(u == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String jwt_username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(jwt_username.equals(username)) {

            // change fields
            if(user.getUsername() != null)
                return new ResponseEntity<>("Username cannot be changed", HttpStatus.BAD_REQUEST);

            if(user.getPassword() != null)
                u.setPassword(passwordEncoder.encode(user.getPassword()));

            if(user.getEmail() != null)
                u.setEmail(user.getEmail());

            if(user.getPhoneNumber() != null)
                u.setPhoneNumber(user.getPhoneNumber());

            if(user.getDateOfBirth() != null)
                u.setDateOfBirth(user.getDateOfBirth());

            if(user.getAboutMe() != null)
                u.setAboutMe(user.getAboutMe());

            if(user.getImage() != null)
                u.setImage(user.getImage());

            if(user.getLanguages() != null)
                u.setLanguages(user.getLanguages());

            try {
                userService.save(u);
            } catch (Exception e) {
                return new ResponseEntity<>("Not modified, some constraints were violated", HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>("The user was successfully edited", HttpStatus.OK);

        } else {

            return new ResponseEntity<>("You're not authorized to modify this user", HttpStatus.UNAUTHORIZED);

        }

    }

    private User recursionTreatment(User u) {
        User user = (User) u.clone();

        for(Scheduling scheduling : user.getSchedules()){
            Tour t = (Tour) scheduling.getTour().clone();
            tourTreatment(t);
            scheduling.setTour(t);
            scheduling.setSignees(null);
            scheduling.setQueue(null);
        }

        for(Tour tour : user.getTours())
            tourTreatment(tour);

        return user;
    }

    private void tourTreatment(Tour tour) {
        System.out.println(tour.getCity());
        City city = (City) tour.getCity().clone();
        city.setTours(null);
        city.setCountry(null);
        tour.setCity(city);
        User u = (User) tour.getGuide().clone();
        u.setSchedules(null);
        u.setTours(null);
        tour.setGuideUsername(u);
        tour.setActive(null);
        tour.setFinished(null);
        tour.setLanguages(null);
        //tour.setImages(null);
        tour.setRoute(null);
        tour.setReviews(null);
    }

}
