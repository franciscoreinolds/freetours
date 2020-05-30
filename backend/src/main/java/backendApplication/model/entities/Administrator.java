package backendApplication.model.entities;

import javax.persistence.*;

@Entity(name = "Administrator")
public class Administrator{
    @Id
    private String email;
    private String password;
}
