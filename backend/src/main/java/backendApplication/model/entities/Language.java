package backendApplication.model.entities;

import javax.persistence.*;

@Entity(name = "Language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String logo;
    private String abbreviation;
}
