package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Commentaire;
import com.example.ApiTourist.model.Pays;

import java.util.List;

public interface CommentaireService {

    Commentaire Ajout(Commentaire commentaire);
    List<Commentaire> liste();
    Commentaire Modifier(Commentaire Commentaire,Long id_commentaire);
    String Supprimer(Long id_Commentaire);

}
