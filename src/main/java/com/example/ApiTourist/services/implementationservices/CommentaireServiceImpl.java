package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Commentaire;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.model.Utilisateur;
import com.example.ApiTourist.repository.CommentaireRepository;
import com.example.ApiTourist.repository.UtilisateurRepository;
import com.example.ApiTourist.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public void ajout(Region region, String username, String contenu) {
        Commentaire commentaire = new Commentaire();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        commentaire.setContenu(contenu);
        commentaire.setUtilisateur(utilisateur);
        commentaire.setDate(new Date());
        commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> liste() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire Modifier(Commentaire Commentaire, Long id_commentaire) {
        Commentaire commentaire=this.commentaireRepository.findById(id_commentaire).orElseThrow();
        commentaire.setContenu(commentaire.getContenu());
        return commentaireRepository.save(commentaire);
    }

    @Override
    public String Supprimer(Long id_Commentaire) {
         commentaireRepository.deleteById(id_Commentaire);
         return "Commetaire supprimé avec succès";
    }

}
