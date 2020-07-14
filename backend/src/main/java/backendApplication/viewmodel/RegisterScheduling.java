package backendApplication.viewmodel;


import backendApplication.model.entities.Scheduling;

import java.time.LocalDateTime;
import java.util.Date;

public class RegisterScheduling {
    private LocalDateTime date;
    private int nrPeople;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getNrPeople() {
        return nrPeople;
    }

    public void setNrPeople(int nrPeople) {
        this.nrPeople = nrPeople;
    }
}
