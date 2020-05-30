package backendApplication.model.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity(name = "Tour")
public class Tour {
    @Id
    private int id;
    private String description;
    private Time duration;
    private int maxCapacity;
    private int minCapacity;
    private String qrCode;

    //private Set<String> images;

    @OneToMany
    private List<Place> route;

    @OneToOne
    private Category category;

    @OneToMany
    private List<Language> languages;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<Scheduling> finished;

    @OneToMany
    private List<Scheduling> active;
}
