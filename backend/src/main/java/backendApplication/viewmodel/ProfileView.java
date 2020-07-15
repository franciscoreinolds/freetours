package backendApplication.viewmodel;

import backendApplication.model.entities.Language;
import backendApplication.model.entities.Tour;
import backendApplication.model.entities.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProfileView {
    private String username;
    private LocalDate dateOfBirth;
    private String aboutMe;
    private String image;
    private Set<Language> languages;
    private Set<Tour> tours;

    public ProfileView(User user) {
        this.username = user.getUsername();
        this.dateOfBirth = user.getDateOfBirth();
        this.aboutMe = user.getAboutMe();
        this.image = user.getImage();
        this.languages = user.getLanguages();
        this.tours = user.getTours();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }
}
