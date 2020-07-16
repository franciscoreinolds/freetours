package backendApplication.model;


import backendApplication.model.dao.TourService;
import backendApplication.model.entities.Scheduling;
import backendApplication.model.entities.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.Future;




@Service
public class SwapManager {

    @Autowired
    TourService tourService;

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
            System.out.println("completou o schedule");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}