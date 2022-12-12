package com.example.ApiTourist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity/*Cette annotation spécifie que la classe est une entité : et sera enregistré dans la BDD */
@Getter/*de lombok */
@Setter/*de lombok */
public class Commentaire {

    @Id /*Cette annotation spécifie la clé primaire de l’entité :*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Cette annotation spécifie les stratégies de génération pour les valeurs
    des clés primaires : La valeur peut être AUTO, TABLE, SEQUENCE ou IDENTITY. */
    private long id;
    private String nom;
    private Date date = new Date();

    @ManyToOne
    private Region region;

    @ManyToOne
    private Utilisateur utilisateur;

}
