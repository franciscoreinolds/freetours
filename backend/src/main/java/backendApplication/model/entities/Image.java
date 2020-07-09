package backendApplication.model.entities;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity(name = "Image")
public class Image{
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="image_sequence")
    //@SequenceGenerator(name="image_sequence", sequenceName="image_seq")
    private int id;
    @NotNull
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
