package backendApplication.model;

import javax.persistence.*;

@Entity(name = "Languages")
public class Language {
    @Id
    private int id;
    private String name;
    private String logo;
    private String abbreviation;
}
