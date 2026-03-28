package spring_security_jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // Issam : Constructeur par défaut (obligatoire pour JPA)
    public Role() {}

    // Issam : Constructeur avec nom (sans id)
    public Role(String name) {
        this.name = name;
    }

    // Issam : Constructeur complet
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}