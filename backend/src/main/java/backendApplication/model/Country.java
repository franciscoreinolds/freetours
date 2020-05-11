package backendApplication.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Countries")
public class Country{
    @Id
    private int id;
    private String name;

    // private Continent continent;

    @OneToMany
    private List<City> cities;
}
