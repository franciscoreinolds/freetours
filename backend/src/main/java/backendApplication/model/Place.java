package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Places")
public class Place {
    @Id
    private int id;
    private String name;
    private double latitude;
    private double longitude;
}
