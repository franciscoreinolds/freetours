package backendApplication.model.entities;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "Scheduling")
public class Scheduling {
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scheduling_sequence")
    //@SequenceGenerator(name="scheduling_sequence", sequenceName="scheduling_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private LocalDateTime date;

    @ManyToOne
    @NotNull
    private Tour tour;

    @ManyToMany
    private List<User> queue;

    @ManyToMany
    private List<User> signees;

    public Scheduling() {
    }

    public Scheduling(Scheduling s){
        this.id = s.getId();
        this.date = s.getDate();
        this.tour = s.getTour();
        this.queue = s.getQueue();
        this.signees = s.getSignees();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public void removeQueue(User user) {this.queue.remove(user);}

    public void removeSignee(User user) {this.signees.remove(user);}

    @Override
    public Object clone(){
        return new Scheduling(this);
    }

    @Override
    public String toString() {
        return "Scheduling{" +
                "id=" + id +
                ", date=" + date +
                ", tour=" + tour +
                ", queue=" + queue +
                ", signees=" + signees +
                '}';
    }
}
