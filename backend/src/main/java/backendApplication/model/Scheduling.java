package backendApplication.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Scheduling")
public class Scheduling {
    @Id
    private int id;
    private Date date;
    private boolean paid;

    @OneToOne
    private Tour tour;

    @OneToMany
    private List<User> queue;

    @OneToMany
    private List<User> signees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<User> getQueue() {
        return queue;
    }

    public void setQueue(List<User> queue) {
        this.queue = queue;
    }

    public List<User> getSignees() {
        return signees;
    }

    public void setSignees(List<User> signees) {
        this.signees = signees;
    }
}
