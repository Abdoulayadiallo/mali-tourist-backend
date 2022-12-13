package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Region;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RegionService {
    Region ajout(Region region,String PostImageName);

    List<Region> lister();
    Region Modifier(Region region , Long id);
    String SupprimerbyId(Long id);

    Iterable<Object[]> listersanspays();

    String savePostImage(MultipartFile multipartFile, String fileName);
}
