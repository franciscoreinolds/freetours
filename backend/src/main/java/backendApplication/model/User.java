package backendApplication.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "User")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String aboutMe;
    private String image;

    @OneToMany
    private List<Language> languages;

    @OneToMany
    private List<Scheduling> schedules;

    @OneToMany
    private List<Tour> tours;
}
