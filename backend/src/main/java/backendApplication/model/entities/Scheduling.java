package backendApplication.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Scheduling")
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
