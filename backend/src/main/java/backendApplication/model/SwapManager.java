package backendApplication.model;


import backendApplication.model.dao.TourService;
import backendApplication.model.entities.Scheduling;
import backendApplication.model.entities.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;


@Service
public class SwapManager {

    @Autowired
    TourService tourService;

    @Async
    public CompletableFuture addSchedule(Scheduling scheduling) throws InterruptedException{
        //long finishesIn = scheduling.getDate().getTime() - new Date().getTime();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime schedulingMoment = scheduling.getDate();
        Duration duration = Duration.between(now, schedulingMoment);
        long finishesIn = Math.abs(duration.toMinutes());

        System.out.println(finishesIn);
        Thread.sleep(finishesIn);
        Tour t = scheduling.getTour();
        t.removeActive(scheduling);
        t.addFinished(scheduling);
        tourService.save(t);
        System.out.println("completou o schedule");
        return CompletableFuture.completedFuture(null);
    }
}