package backendApplication.viewmodel;


import backendApplication.model.entities.Scheduling;

import java.time.LocalDateTime;
import java.util.Date;

public class RegisterScheduling {
    private int scheduleId;
    private int nrPeople;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getNrPeople() {
        return nrPeople;
    }

    public void setNrPeople(int nrPeople) {
        this.nrPeople = nrPeople;
    }
}
