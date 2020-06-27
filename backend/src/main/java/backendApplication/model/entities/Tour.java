package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Entity(name = "Tour")
public class Tour {
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
    private int duration;
    @NotNull
    private int maxCapacity;
    @NotNull
    private int minCapacity;
    private String qrCode;

    @NotNull
    private String guideUsername;

    @OneToOne
    @NotNull
    private City city;

    @OneToMany
    @NotNull
    private Set<Image> images;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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

    public String getGuideUsername() {
        return guideUsername;
    }

    public void setGuideUsername(String guideUsername) {
        this.guideUsername = guideUsername;
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
