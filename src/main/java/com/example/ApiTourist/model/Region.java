package com.example.ApiTourist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Entity/*Cette annotation spécifie que la classe est une entité : et sera enregistré dans la BDD */
@Getter/*de lombok */
@Setter/*de lombok */
@ToString
public class Region implements Serializable{
    private static final long serialVersionUID = 164669782975869L;

    @Id /*Cette annotation spécifie la clé primaire de l’entité :*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Cette annotation spécifie les stratégies de génération pour les valeurs
    des clés primaires : La valeur peut être AUTO, TABLE, SEQUENCE ou IDENTITY. */
    private long id;
    private String image;
    private String coderegion;
    private String nomregion;
    private String activité;
    private String Superficie;
    private String langue;
    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    private Pays pays;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Commentaire> commentaires;

    @ManyToMany
    @JoinTable(
            name = "RegionPopulation",
            joinColumns = @JoinColumn(name="region_id"),
            inverseJoinColumns = @JoinColumn(name = "population_id")
    )
    List<Population> populations;

    @JsonIgnore
    public void setCommentaires(Commentaire commentaire) {
        if (commentaire != null) {
            this.commentaires.add(commentaire);
        }
    }
}
