package backendApplication.model.entities;

import javax.persistence.*;

@Entity(name = "Review")
public class Review{
    @Id
    private int id;
    private String comment;
    private float rating;

    @OneToOne
    private User user;
}
