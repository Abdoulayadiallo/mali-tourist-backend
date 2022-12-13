package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.repository.RegionRepository;
import com.example.ApiTourist.services.RegionService;
import com.example.ApiTourist.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
public class RegionImpl implements RegionService {
    @Autowired
    RegionRepository regionRepository;
    @Override
    public Region ajout(Region region,String PostImageName) {
        return regionRepository.save(region);
    }

    @Override
    public List<Region> lister() {
        return regionRepository.findAll();
    }

    @Override
    public Region Modifier(Region region, Long id) {
        Region r = this.regionRepository.findById(id).orElseThrow();
        r.setActivité(region.getActivité());
        r.setNomregion(region.getNomregion());
        r.setCoderegion(region.getCoderegion());
        r.setLangue(region.getLangue());
        r.setSuperficie(region.getSuperficie());
        return regionRepository.save(r);
    }

    @Override
    public String SupprimerbyId(Long id) {
       this.regionRepository.deleteById(id);
       return "Region supprimée avec succes";
    }

    @Override
    public Iterable<Object[]> listersanspays() {
        return regionRepository.MesRegionsansPays();
    }

    @Override
    public String savePostImage(MultipartFile multipartFile, String fileName) {
        /*
         * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
         * request; Iterator<String> it = multipartRequest.getFileNames(); MultipartFile
         * multipartFile = multipartRequest.getFile(it.next());
         */

        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(Constants.REGION_FOLDER + fileName + ".png");
            Files.write(path, bytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite. La photo n'a pas été enregistrée !");
            return "Une erreur s'est produite. La photo n'a pas été enregistrée !";
        }
        System.out.println("Photo enregistré avec succès");
        return "Photo enregistré avec succès!";
    }

}
