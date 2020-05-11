package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Administrator")
public class Administrator{
    @Id
    private String email;
    private String password;
}
