package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface AccountService {
    public Utilisateur saveUser(String nom,String prenom, String username, String email);

    public Utilisateur findByUsername(String username);

    public Utilisateur findByEmail(String userEmail);

    public List<Utilisateur> userList();

    public Role findUserRoleByName(String string);

    public Role saveRole(Role role);

    public void updateUserPassword(Utilisateur utilisateur, String newpassword);

    public Utilisateur updateUser(Utilisateur utilisateur, HashMap<String, String> request);

    public Utilisateur simpleSaveUser(Utilisateur utilisateur);

    public Utilisateur findUserById(Long id);

    public void deleteUser(Utilisateur utilisateur);

    public void resetPassword(Utilisateur utilisateur);

    public List<Utilisateur> getUsersListByUsername(String username);

    public String saveUserImage(MultipartFile multipartFile, Long userImageId);

    Role addNewRole(Role Role);
}
