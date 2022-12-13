package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByUsername(String username);

    Utilisateur findByEmail(String userEmail);

    @Query("SELECT u FROM Utilisateur u WHERE u.id=:x")
    public Utilisateur findUserById(@Param("x") Long id);

    List<Utilisateur> findByUsernameContaining(String username);
}
