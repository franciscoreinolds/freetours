package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Place")
public class Place {
    @Id
    private int id;
    private String name;
    private double latitude;
    private double longitude;
}
