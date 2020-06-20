package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Continent")
public class Continent{
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="country_sequence")
    //@SequenceGenerator(name="country_sequence", sequenceName="country_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ContinentEnum name;

    public Continent() {
    }
  
    public Continent(ContinentEnum name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public ContinentEnum getName() {
        return name;
    }

    public void setName(ContinentEnum name) {
        this.name = name;
    }

}
