package backendApplication.model.entities;

import javax.persistence.*;

@Entity(name = "Category")
public class Category{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_sequence")
    @SequenceGenerator(name="category_sequence", sequenceName="category_seq")
    private int id;
    @Column(unique=true)
    private String name;

    public Category(){
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
}
