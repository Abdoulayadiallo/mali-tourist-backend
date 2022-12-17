package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Erole;
import com.example.ApiTourist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(Erole roleName);
}
