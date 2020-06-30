package backendApplication.viewmodel;


import backendApplication.model.entities.Scheduling;

import java.util.Date;

public class RegisterScheduling {
    private Date date;
    private int nrPeople;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNrPeople() {
        return nrPeople;
    }

    public void setNrPeople(int nrPeople) {
        this.nrPeople = nrPeople;
    }
}
