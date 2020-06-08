package backendApplication.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "City")
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double lattitude;
    private Double longitude;

    @OneToOne
    private Country country;

    @OneToMany
    private List<Tour> tours;
}
