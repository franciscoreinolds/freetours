package backendApplication.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "City")
public class City{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="city_sequence")
    @SequenceGenerator(name="city_sequence", sequenceName="city_seq")
    private int id;
    private String name;

    @OneToOne
    private Country country;

    @OneToMany
    private List<Tour> tours;

    public City() {
    }

    public City(String name) {
        this.name = name;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
