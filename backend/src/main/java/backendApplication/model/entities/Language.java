package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "Language")
public class Language {
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="language_sequence")
    //@SequenceGenerator(name="language_sequence", sequenceName="language_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    @NotNull
    private String name;
    @Column(unique=true)
    @NotNull
    private String logo;
    @Column(unique=true)
    @NotNull
    private String abbreviation;

    public Language() {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
