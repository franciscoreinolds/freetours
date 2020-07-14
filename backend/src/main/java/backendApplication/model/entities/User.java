package backendApplication.model.entities;

import com.sun.istack.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToMany
    private Set<Language> languages;

    @ManyToMany
    private List<Scheduling> schedules;

    @ManyToMany
    private List<Tour> tours;

    public User() {
    }

    public User(User u) {
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.email = u.getEmail();
        this.phoneNumber = u.getPhoneNumber();
        this.dateOfBirth = u.getDateOfBirth();
        this.aboutMe = u.getAboutMe();
        this.image = u.getImage();
        this.languages = u.getLanguages();
        this.schedules = u.getSchedules();
        this.tours = u.getTours();
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
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
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
                ", languages=" + languages + "\n" +
                ", schedules=" + schedules + "\n" +
                ", tours=" + tours + "\n" +
                '}' + "\n";
    }

    @Override
    public Object clone(){
        return new User(this);
    }

    public void addTour(Tour tour) {this.tours.add(tour);}

    public void addScheduling(Scheduling scheduling) {this.schedules.add(scheduling);}

    public void removeTour(Tour tour) {this.tours.remove(tour);}

    public void removeScheduling(Scheduling scheduling) {this.schedules.remove(scheduling);}

    @Override
    public Object clone(){
        return new User(this);
    }

    // Returns next x schedule tours, from the current date
    public List<Scheduling> getNextTours(int x) {
        List<Scheduling> nextTours = schedules.stream()
                        .filter(scheduling -> scheduling.getDate().isAfter(LocalDateTime.now())) // filtra os schedules que ja foram
                        .collect(Collectors.toList());

        if(nextTours.size() > x) {
            return nextTours.subList(0,x);
        }else {
            return nextTours;
        }
    }

    public boolean allParametersFilled(){
        if (this.username != null && this.password != null && this.email != null &&
                this.phoneNumber != null && this.dateOfBirth != null && this.aboutMe != null &&
                this.image != null && this.languages != null) return true;
        else return false;
    }
}
