package com.example.ApiTourist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Pays implements Serializable {
    private static final long serialVersionUID = 164669782975869L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pays;

    private String nom_pays;

    @Column(columnDefinition = "text")
    private String description;

   // @OneToMany(mappedBy = "pays")
    //List<Region> regionList;



}
