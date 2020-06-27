package backendApplication.viewmodel;

import backendApplication.model.entities.Language;
import backendApplication.model.entities.Tour;
import backendApplication.model.entities.User;

import java.util.Date;
import java.util.List;

public class ProfileView {
    private String username;
    private Date dateOfBirth;
    private String aboutMe;
    private String image;
    private List<Language> languages;
    private List<Tour> tours;

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

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
