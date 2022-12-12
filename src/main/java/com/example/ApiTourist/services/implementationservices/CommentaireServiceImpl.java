package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Commentaire;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.repository.CommentaireRepository;
import com.example.ApiTourist.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;
    @Override
    public Commentaire Ajout(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> liste() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire Modifier(Commentaire Commentaire, Long id_commentaire) {
        Commentaire commentaire=this.commentaireRepository.findById(id_commentaire).orElseThrow();
        commentaire.setNom(Commentaire.getNom());
        return commentaireRepository.save(commentaire);
    }

    @Override
    public String Supprimer(Long id_Commentaire) {
         commentaireRepository.deleteById(id_Commentaire);
         return "Commetaire supprimé avec succès";
    }

}
