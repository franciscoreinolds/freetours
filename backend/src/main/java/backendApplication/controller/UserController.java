package backendApplication.controller;

import backendApplication.model.ImageStoreService;
import backendApplication.model.Pair;
import backendApplication.model.dao.*;
import backendApplication.model.entities.*;
import backendApplication.viewmodel.ProfileView;
import backendApplication.viewmodel.ProfileViewAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageStoreService imageStoreService;

    @Autowired
    private TourService tourService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PlaceService placeService;

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
    public ResponseEntity<String> editProfile(@PathVariable String username, @RequestPart User user, @RequestPart(required = false) MultipartFile profileImage) {


        User u = userService.get(username);
        if(u == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String jwt_username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(jwt_username.equals(username)) {

            if(user.getEmail() != null)
                u.setEmail(user.getEmail());

            if(user.getPhoneNumber() != null)
                u.setPhoneNumber(user.getPhoneNumber());

            if(user.getDateOfBirth() != null)
                u.setDateOfBirth(user.getDateOfBirth());

            if(user.getAboutMe() != null)
                u.setAboutMe(user.getAboutMe());

            if(user.getLanguages() != null)
                u.setLanguages(user.getLanguages());

            if(profileImage != null && !profileImage.isEmpty()) {

                List<String> filenames = imageStoreService.storeImage(List.of(new Pair<>(profileImage, user.getUsername() + ".png")));

                user.setImage(filenames.get(0));
            }

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
        City city = (City) tour.getCity().clone();
        city.setTours(null);
        city.setCountry(null);
        tour.setCity(city);
        User u = (User) tour.getGuide().clone();
        u.setSchedules(null);
        u.setTours(null);
        tour.setGuide(u);
        tour.setActive(null);
        tour.setFinished(null);
        tour.setLanguages(null);
        //tour.setImages(null);
        tour.setRoute(null);
        tour.setReviews(null);
    }


    /*
     * Post method for delete a user
     *
     * @param username String that represents the username which is a PathVariable
     *
     * @return http status that shows if the operation was sucessfull
     * */
    @RequestMapping(value = "/profile/delete_account", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteAccount(@RequestBody User user) {

        try {

            String jwt_username = SecurityContextHolder.getContext().getAuthentication().getName();

            String username = user.getUsername();
            if (jwt_username.equals(username)){
                User u = userService.get(username);
                for(Scheduling s: u.getSchedules()){
                   s.getSignees().removeIf(user1-> user1.getUsername() == u.getUsername());
                   s.getQueue().removeIf(user1-> user1.getUsername() == u.getUsername());
                }

                Set<Tour> trs = u.getTours();
                u.setTours(null);
                for(Tour t : trs){

                    City c = t.getCity();
                    c.getTours().removeIf(tour-> tour.getId() == t.getId());

                    for(Scheduling s: t.getActive()){
                        s.setTour(null);
                    }
                    for(Scheduling s: t.getFinished()){
                        s.setTour(null);
                    }

                    Set<Review> rvs = t.getReviews();
                    t.setReviews(null);
                    for(Review r: rvs){
                        reviewService.delete(r.getId());
                    }

                    Set<Place> pls = t.getRoute();
                    t.setRoute(null);
                    for(Place p: pls){
                        placeService.delete(p.getId());
                    }

                    for(Language l: t.getLanguages()){
                        l.getTours().removeIf(tour -> tour.getId() == t.getId());
                    }

                    tourService.delete(t.getId());
                }
                u.setLanguages(null);
                userService.delete(username);
                return new ResponseEntity<> (HttpStatus.OK);
            }

            return new ResponseEntity<> (HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException e){

            return  new ResponseEntity<> (HttpStatus.BAD_REQUEST);

        }
    }
}
