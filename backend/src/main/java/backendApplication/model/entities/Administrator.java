package backendApplication.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "Administrator")
public class Administrator{
    @Id
    @Column(unique=true) // required?
    private String email;
    @NotNull
    private String password;

    public Administrator() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
