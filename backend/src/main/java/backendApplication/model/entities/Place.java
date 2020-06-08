package backendApplication.model.entities;

import javax.persistence.*;

@Entity(name = "Place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double latitude;
    private double longitude;
}
