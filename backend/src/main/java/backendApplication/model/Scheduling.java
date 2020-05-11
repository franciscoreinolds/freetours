package backendApplication.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Scheduling")
public class Scheduling {
    @Id
    private int id;
    private Date date;
    private boolean paid;

    @OneToOne
    private Tour tour;

    @OneToMany
    private List<User> queue;

    @OneToMany
    private List<User> signees;
}
