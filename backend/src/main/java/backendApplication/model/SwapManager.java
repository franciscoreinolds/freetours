package backendApplication.model;


import backendApplication.model.dao.ReviewService;
import backendApplication.model.dao.TourService;
import backendApplication.model.emailBuilder.Email;
import backendApplication.model.emailBuilder.EmailDirector;
import backendApplication.model.emailBuilder.ReviewEmail;
import backendApplication.model.entities.Review;
import backendApplication.model.entities.Scheduling;
import backendApplication.model.entities.Tour;
import backendApplication.model.entities.User;
import backendApplication.model.mailer.MailerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class SwapManager {

    @Autowired
    TourService tourService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    private MailerContext mailerContext;

    @Autowired
    private Environment env;

    @Async("threadPoolTaskExecutor")
    public void addSchedule(Scheduling scheduling){
        //long finishesIn = scheduling.getDate().getTime() - new Date().getTime();

        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime schedulingMoment = scheduling.getDate();
        Duration duration = Duration.between(now, schedulingMoment);
        long finishesIn = Math.abs(duration.toMillis());
  
        System.out.println(finishesIn);
        try {
            Thread.sleep(finishesIn);
            Tour t = tourService.get(scheduling.getTour().getId());
            t.removeActive(scheduling);
            t.addFinished((Scheduling) scheduling.clone());
            tourService.save(t);

            sendReviewMail(t, scheduling);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendReviewMail(Tour tour, Scheduling scheduling) {

        for(User user : scheduling.getSignees()) {

            // create a review
            Review review = new Review();
            Review review1;
            String token;
            do {

                token = UUID.randomUUID().toString();
                review1 = reviewService.findByToken(token);

            } while(review1 != null);

            review.setToken(token);
            review.setUser(user);
            review.setTour(tour);
            reviewService.save(new Review());

            // link review to the specific tour
            tour.addReview(review);
            tourService.save(tour);

            // send email
            EmailDirector builder = new EmailDirector(new ReviewEmail());
            Email email = builder.createEmail(env.getProperty("app.email"), user.getEmail(), null, env.getProperty("frontend.url") + "/#/review/" + token);
            mailerContext.send(email);

        }

    }

}