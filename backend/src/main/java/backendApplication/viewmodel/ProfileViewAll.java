package backendApplication.viewmodel;

import backendApplication.model.entities.Scheduling;
import backendApplication.model.entities.User;

import java.util.List;

public class ProfileViewAll extends ProfileView{
    private String email;
    private String phoneNumber;
    private List<Scheduling> schedules;

    public ProfileViewAll(User user) {
        super(user);
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.schedules = user.getSchedules();
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

    public List<Scheduling> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Scheduling> schedules) {
        this.schedules = schedules;
    }
}
