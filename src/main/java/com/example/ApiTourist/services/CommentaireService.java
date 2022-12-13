package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Commentaire;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Region;

import java.util.List;


public interface CommentaireService {

    void ajout(Region region, String username, String content);
    List<Commentaire> liste();
    Commentaire Modifier(Commentaire Commentaire,Long id_commentaire);
    String Supprimer(Long id_Commentaire);

}
