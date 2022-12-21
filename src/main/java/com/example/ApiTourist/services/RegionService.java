package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Region;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface RegionService {
    Region ajout(Region region, String postImageName);

    List<Region> lister();
    Region Modifier(Region region , Long id);
    Region SupprimerbyId(Region region);

    Iterable<Object[]> listersanspays();

    String saveRegionImage(MultipartFile multipartFile, String fileName);

    Region getRegionById(Long id);

}
