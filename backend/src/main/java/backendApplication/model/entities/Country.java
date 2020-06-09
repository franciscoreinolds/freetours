package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Country")
public class Country{
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="country_sequence")
    //@SequenceGenerator(name="country_sequence", sequenceName="country_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    @NotNull
    private String name;

    // private Continent continent;

    @OneToMany
    private List<City> cities;

    public Country() {
    }

    public Country(String name){
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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
