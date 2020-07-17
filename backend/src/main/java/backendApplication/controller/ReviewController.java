package backendApplication.controller;

import backendApplication.model.dao.ReviewService;
import backendApplication.model.dao.TourService;
import backendApplication.model.dao.UserService;
import backendApplication.model.entities.Review;
import backendApplication.model.entities.Tour;
import backendApplication.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private TourService tourService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public ResponseEntity<?> rateTour(@RequestParam("token") String token, @RequestBody Review review) {

        // check token
        Review review1 = reviewService.findByToken(token);
        if(review1 == null)
            return new ResponseEntity<>("Token is not valid", HttpStatus.NOT_FOUND);

        // check review
        if(review.getRating() <= 0.0 || review.getRating() > 5.0)
            return new ResponseEntity<>("Rating should be between 0.0 and 5.0", HttpStatus.BAD_REQUEST);

        // check the existing review
        if(review1.isDone())
            return new ResponseEntity<>("The review has already been done. You cannot use this token again.", HttpStatus.NOT_ACCEPTABLE);

        // save review
        review1.setComment(review.getComment());
        review1.setRating(review.getRating());
        review1.setDone(true);
        reviewService.save(review1);

        // compute ratings
        Tour tour = review1.getTour();
        tour.setRating(tour.computeRating());
        tourService.save(tour);
        User guide = tour.getGuide();
        guide.setRating(guide.computeRating());
        userService.save(guide);

        return new ResponseEntity<>("Review added to tour", HttpStatus.OK);
    }

}
