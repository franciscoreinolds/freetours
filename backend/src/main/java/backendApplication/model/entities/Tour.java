package backendApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

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
    private LocalTime duration;
    @NotNull
    private int maxCapacity;
    @NotNull
    private int minCapacity;
    private String qrCode;
    @NotNull
    private float rating;

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
    private Set<Place> route;

    @OneToOne
    @NotNull
    private Category category;

    @ManyToMany
    @NotNull
    private Set<Language> languages;

    @OneToMany
    private Set<Review> reviews;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Scheduling> finished;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Scheduling> active;

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
        this.rating = t.getRating();
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

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public User getGuide() {
        return guide;
    }

    public void setGuide(User guide) {
        this.guide = guide;
    }

    public void setGuideUsername(User guide) {
        this.guide = guide;
    }


    public Set<Place> getRoute() {
        return route;
    }

    public void setRoute(Set<Place> route) {
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

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Scheduling> getFinished() {
        return finished;
    }

    public void setFinished(Set<Scheduling> finished) {
        this.finished = finished;
    }

    public Set<Scheduling> getActive() {
        return active;
    }

    public void setActive(Set<Scheduling> active) {
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


    public void removeActive(Scheduling scheduling) {
        System.out.println(this.active.removeIf(s -> s.getId() == scheduling.getId()));
    }

    public void addFinished(Scheduling scheduling) {
        this.finished.add(scheduling);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addImage(Image image) {this.images.add(image);}

    @Override
    public Object clone(){
        return new Tour(this);
    }

    @JsonIgnore
    public boolean hasActiveAfter(LocalDate fromDate) {
        return this.active.stream().filter(s -> s.getDate().isAfter(fromDate.atStartOfDay()))
                .count() > 0;
    }

    @JsonIgnore
    public boolean hasActiveBefore(LocalDate untilDate) {
        return this.active.stream().filter(s -> s.getDate().isBefore(untilDate.atStartOfDay()))
                .count() > 0;
    }

    @JsonIgnore
    public float computeRating() {

        float sum = 0;
        for(Review review : this.getReviews())
            sum += review.getRating();

        return sum / this.getReviews().size();

    }

}
