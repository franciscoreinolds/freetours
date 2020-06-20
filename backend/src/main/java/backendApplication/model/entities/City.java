package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity(name = "City")
public class City{
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="city_sequence")
    //@SequenceGenerator(name="city_sequence", sequenceName="city_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;

    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
  
    @OneToOne
    @NotNull
    private Country country;

    @OneToMany
    private List<Tour> tours;

    public City() {
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
