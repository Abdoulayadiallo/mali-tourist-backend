package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findAllById(Long id);

    Region getRegionById(Long id);


    @Query(value ="SELECT region.nomregion,region.coderegion, pays.id_pays From region,pays WHERE pays_id_pays=pays.id_pays ",nativeQuery = true)
    Iterable<Object[]> MesRegionsansPays();

   @Query(value = "SELECT region.nomregion,region.id,region.coderegion,region.superficie, region.langue, region. activité From region,pays WHERE pays_id_pays=pays.id_pays ",nativeQuery = true)
   List<Object[]>  MesRegionsavecPays();

    @Query("SELECT r FROM Region r WHERE r.id=:x")
    public Region findRegionById(@Param("x") Long id);

    @Modifying
    @Query("DELETE Region WHERE id=:x")
    public void deleteRegionById(@Param("x") Long id);
}
