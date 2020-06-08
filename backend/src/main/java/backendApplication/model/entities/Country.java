package backendApplication.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Country")
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // private Continent continent;

    @OneToMany
    private List<City> cities;
}
