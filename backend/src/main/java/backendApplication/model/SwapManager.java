package backendApplication.model;


import backendApplication.model.entities.Scheduling;
import backendApplication.model.entities.Tour;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SwapManager {

    public void addSchedule(Scheduling s) {
        long finishesIn = s.getDate().getTime() - new Date().getTime();
        new FinishSchedule(s, finishesIn).start();
    }
}

class FinishSchedule extends Thread {

    private Scheduling scheduling;
    private long finishesIn;

    public FinishSchedule(Scheduling s, long finishesIn) {
        this.scheduling = s;
        this.finishesIn = finishesIn;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.finishesIn);
            Tour t = scheduling.getTour();
            t.removeActive(this.scheduling);
            t.addFinished(this.scheduling);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
