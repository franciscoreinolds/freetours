package backendApplication.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Cities")
public class City{
    @Id
    private int id;
    private String name;

    @OneToOne
    private Country country;

    @OneToMany
    private List<Tour> tours;
}
