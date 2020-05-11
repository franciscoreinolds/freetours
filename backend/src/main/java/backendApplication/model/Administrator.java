package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Administrators")
public class Administrator{
    @Id
    private String email;
    private String password;
}
