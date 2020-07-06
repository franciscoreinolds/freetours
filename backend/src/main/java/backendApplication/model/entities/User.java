package backendApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name="User_")
public class User implements UserDetails {
    @Id
    @Column(unique=true) // required?
    private String username;
    @NotNull
    private String password;
    @Column(unique=true)
    @NotNull
    private String email;
    @Column(unique=true)
    private String phoneNumber;
    private Date dateOfBirth;
    private String aboutMe;
    private String image;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user__languages",
            joinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_username", "language_id"})}
    )
    private Set<Language> user__languages;

    @OneToMany
    @JsonIgnore
    private List<Scheduling> schedules;

    @OneToMany(
            fetch = FetchType.EAGER
    )
    private List<Scheduling> schedules;

    @ManyToMany
    private List<Tour> tours;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Language> getLanguages() {
        return user__languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.user__languages = languages;
    }

    public List<Scheduling> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Scheduling> schedules) {
        this.schedules = schedules;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "username='" + username + "'\n" +
                ", password='" + password + "'\n" +
                ", email='" + email + "'\n" +
                ", phoneNumber='" + phoneNumber + "'\n" +
                ", dateOfBirth=" + dateOfBirth + "\n" +
                ", aboutMe='" + aboutMe + "'\n" +
                ", image='" + image + "'\n" +
                ", languages=" + user__languages + "\n" +
                ", schedules=" + schedules + "\n" +
                ", tours=" + tours + "\n" +
                '}' + "\n";
    }

    public void addTour(Tour tour) {this.tours.add(tour);}

    public void addScheduling(Scheduling scheduling) {this.schedules.add(scheduling);}


    // Returns next x schedule tours, from the current date
    public List<Scheduling> getNextTours(int x) {
        return schedules.stream()
                        .filter(scheduling -> scheduling.getDate().compareTo(new Date()) > 0) // filtra os schedules que ja foram
                        .collect(Collectors.toList())
                        .subList(0, x);
    }
}
