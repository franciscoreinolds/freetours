package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Scheduling")
public class Scheduling {
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scheduling_sequence")
    //@SequenceGenerator(name="scheduling_sequence", sequenceName="scheduling_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Date date;
    @NotNull
    private boolean paid;

    @ManyToOne
    @NotNull
    private Tour tour;

    @ManyToMany
    private List<User> queue;

    @ManyToMany
    private List<User> signees;

    public Scheduling() {
    }

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

    public void addQueue(User user) {this.queue.add(user);}

    public void addSignee(User user) {this.signees.add(user);}

}
