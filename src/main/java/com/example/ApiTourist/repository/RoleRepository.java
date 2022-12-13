package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findRoleByRoleName(String roleName);
}
