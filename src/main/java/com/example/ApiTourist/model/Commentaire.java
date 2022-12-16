package com.example.ApiTourist.model;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity/*Cette annotation spécifie que la classe est une entité : et sera enregistré dans la BDD */
@Getter/*de lombok */
@Setter/*de lombok */
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 164669782975869L;

    @Id /*Cette annotation spécifie la clé primaire de l’entité :*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Cette annotation spécifie les stratégies de génération pour les valeurs
    des clés primaires : La valeur peut être AUTO, TABLE, SEQUENCE ou IDENTITY. */
    private long id;
    private String contenu;
    @CreationTimestamp
    private Date date;


    @ManyToOne
    private Utilisateur utilisateur;

}
