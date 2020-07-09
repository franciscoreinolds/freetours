package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Tour")
public class Tour implements Serializable {
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tour_sequence")
    //@SequenceGenerator(name="tour_sequence", sequenceName="tour_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Time duration;
    @NotNull
    private int maxCapacity;
    @NotNull
    private int minCapacity;
    private String qrCode;

    @NotNull
    @ManyToOne
    private City city;

    @ManyToOne
    private User guide;

    @ManyToMany
    @NotNull
    private Set<Image> images;

    @OneToMany
    @NotNull
    private List<Place> route;

    @OneToOne
    @NotNull
    private Category category;

    @ManyToMany
    @NotNull
    private Set<Language> languages;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<Scheduling> finished;

    @OneToMany
    private List<Scheduling> active;

    public Tour() {
    }

    public Tour(Tour t) {
        this.id = t.getId();
        this.name = t.getName();
        this.description = t.getDescription();
        this.duration = t.getDuration();
        this.minCapacity = t.getMinCapacity();
        this.maxCapacity = t.getMaxCapacity();
        this.qrCode = t.getQrCode();
        this.active = t.getActive();
        this.route = t.getRoute();
        this.reviews = t.getReviews();
        this.languages = t.getLanguages();
        this.guide = t.getGuide();
        this.images = t.getImages();
        this.category = t.getCategory();
        this.finished = t.getFinished();
        this.city = t.getCity();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getGuide() {
        return guide;
    }

    public void setGuideUsername(User guide) {
        this.guide = guide;
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

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void addActive(Scheduling s) {this.active.add(s);}

    @Override
    public Object clone(){
        return new Tour(this);
    }
}
