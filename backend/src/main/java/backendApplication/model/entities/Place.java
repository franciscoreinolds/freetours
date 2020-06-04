package backendApplication.model.entities;

import javax.persistence.*;

@Entity(name = "Place")
public class Place {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="place_sequence")
    @SequenceGenerator(name="place_sequence", sequenceName="place_seq")
    private int id;
    private String name;
    private double latitude;
    private double longitude;

    public Place() {
    }

    public Place(String name) {
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
}
