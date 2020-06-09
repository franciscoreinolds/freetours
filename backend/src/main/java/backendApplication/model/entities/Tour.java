package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Entity(name = "Tour")
public class Tour {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tour_sequence")
    @SequenceGenerator(name="tour_sequence", sequenceName="tour_seq")
    private int id;
    @NotNull
    private String description;
    @NotNull
    private Time duration;
    @NotNull
    private int maxCapacity;
    @NotNull
    private int minCapacity;
    private String qrCode;

    //private Set<String> images;

    @OneToMany
    @NotNull
    private List<Place> route;

    @OneToOne
    @NotNull
    private Category category;

    @OneToMany
    @NotNull
    private List<Language> languages;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<Scheduling> finished;

    @OneToMany
    private List<Scheduling> active;

    public Tour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public List<Place> getRoute() {
        return route;
    }

    public void setRoute(List<Place> route) {
        this.route = route;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Scheduling> getFinished() {
        return finished;
    }

    public void setFinished(List<Scheduling> finished) {
        this.finished = finished;
    }

    public List<Scheduling> getActive() {
        return active;
    }

    public void setActive(List<Scheduling> active) {
        this.active = active;
    }
}
