package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Categories")
public class Category{
    @Id
    private int id;
    private String name;

    public Category(){
    }

    public Category(String name){
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
}
