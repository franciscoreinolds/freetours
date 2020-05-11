package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Reviews")
public class Review{
    @Id
    private int id;
    private String comment;
    private float rating;

    @OneToOne
    private User user;
}
