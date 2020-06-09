package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "Review")
public class Review{
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="review_sequence")
    //@SequenceGenerator(name="review_sequence", sequenceName="review_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    @NotNull
    private float rating;

    @OneToOne
    @NotNull
    private User user;

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
