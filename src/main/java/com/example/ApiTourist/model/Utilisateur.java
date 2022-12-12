package com.example.ApiTourist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nom;
    private String prenom;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_Role",
            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Role_id", referencedColumnName = "id"))
    private Collection<Role> appRoles = new java.util.ArrayList<>();
}
