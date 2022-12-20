package com.example.ApiTourist.controller;


import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.services.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
/*RestController est utilisé pour créer des services
Web restful à l’aide de l’annotation,
il permet de gérer toutes les API REST telles que les requêtes GET, POST, Delete, PUT.*/

@RequestMapping("/region")
/*Elle est utilisée pour traiter les requêtes HTTP avec des modèles d’URL spécifiés.
Il est utilisé dans et avec les @Controller et les @RestController.*/

@Api(value = "hello", description = "Methodes sur Region")
public class RegionController {
    private String postImageName;
    @Autowired
    RegionService regionService;
    @ApiOperation(value = "Ajouter une region ")
    @PostMapping("/add")
    //*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*//*
    public ResponseEntity<?> ajout(@RequestParam HashMap<String, String> request,@RequestParam("image") MultipartFile multipartFile){

        postImageName = RandomStringUtils.randomAlphabetic(10);
        try {
            regionService.saveRegionImage(multipartFile, postImageName);
            Region region = regionService.ajout(request, postImageName);
            String coderegion = request.get("coderegion");
            String nomregion = request.get("nomregion");
            String activité = request.get("activité");
            String langue = request.get("langue");
            String description = request.get("description");
            String superficie = request.get("superficie");
            region.setCoderegion(coderegion);
            region.setNomregion(nomregion);
            region.setActivité(activité);
            region.setLangue(langue);
            region.setDescription(description);
            region.setSuperficie(superficie);
            System.out.println("Region Ajouté");
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur est survenue: \" + e.getMessage(), HttpStatus.BAD_REQUEST", HttpStatus.BAD_REQUEST);
        }

//        try {
//            Region region = regionService.ajout(request, postImageName);
//            System.out.println("Region Ajouté");
//            return new ResponseEntity<>(region, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Une erreur est survenue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
    }

    @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylist")
    public List<Region> l(){

        return regionService.lister();
    }
    @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylistwithoutp")
    public Iterable<Object[]> regionIterable(){
        return  regionService.listersanspays();
    }

    @ApiOperation(value = "Modifier une region par Id")
    @PutMapping("/update/{id}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Region region,@PathVariable Long id){
        regionService.Modifier(region,id);
                return"mise à jour valider";
    }
    @ApiOperation(value = "Supprimer une region par Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable("id") Long id) {
        Region region = regionService.getPostById(id);
        if (region == null) {
            return new ResponseEntity<>("region non trouvé", HttpStatus.NOT_FOUND);
        }
        try {
            regionService.SupprimerbyId(region);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur est survenue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/image/upload")
    public ResponseEntity<String> fileUpload(@RequestParam("image") MultipartFile multipartFile) {
        try {
            regionService.saveRegionImage(multipartFile, postImageName);
            return new ResponseEntity<>("image de la region enregistrer!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("image de la region n'a pas pu etre enregistrer!", HttpStatus.BAD_REQUEST);
        }
    }


}
