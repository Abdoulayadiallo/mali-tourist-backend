package com.example.ApiTourist.controller;

import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.model.Utilisateur;
import com.example.ApiTourist.services.AccountService;
import com.example.ApiTourist.services.CommentaireService;
import com.example.ApiTourist.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/Commentaire")
public class CommentaireController {
    @Autowired
    private RegionService regionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommentaireService commentaireService;
    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody HashMap<String, String> request) {
        String regionId = request.get("regionId");
        Region region = regionService.getPostById(Long.parseLong(regionId));
        if (region == null) {
            return new ResponseEntity<>("region non trouvé", HttpStatus.NOT_FOUND);
        }
        String username = request.get("username");
        Utilisateur utilisateur = accountService.findByUsername(username);
        if (utilisateur == null) {
            return new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.NOT_FOUND);
        }
        String contenu = request.get("contenu");
        try {
            commentaireService.ajout(region, username, contenu);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Commentaire non ajouté.", HttpStatus.BAD_REQUEST);
        }
    }
}
