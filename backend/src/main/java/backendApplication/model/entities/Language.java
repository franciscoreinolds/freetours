package backendApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(unique=true)
    @NotNull
    private String country_code;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user__languages",
            joinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"language_id", "user_username"})}
    )
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "tour_languages",
            joinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"language_id", "tour_id"})}
    )
    @NotNull
    @JsonIgnoreProperties({"languages"})
    private Set<Tour> tours;

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

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

}
