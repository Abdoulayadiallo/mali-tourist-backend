package com.example.ApiTourist.services.implementationservices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.Utilisateur;
import com.example.ApiTourist.model.UtilisateurRole;
import com.example.ApiTourist.repository.RoleRepository;
import com.example.ApiTourist.repository.UtilisateurRepository;
import com.example.ApiTourist.services.AccountService;
import com.example.ApiTourist.util.Constants;
import com.example.ApiTourist.util.EmailConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EmailConstructor emailConstructor;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Transactional
	public Utilisateur saveUser(String nom,String prenom, String username, String email) {
		String password = RandomStringUtils.randomAlphanumeric(10);
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPassword(encryptedPassword);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setUsername(username);
		utilisateur.setEmail(email);
		Set<UtilisateurRole> utilisateurRoles = new HashSet<>();
		utilisateurRoles.add(new UtilisateurRole(utilisateur, accountService.findUserRoleByName("USER")));
		utilisateur.setUserRoles(utilisateurRoles);
		utilisateurRepository.save(utilisateur);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Constants.TEMP_USER.toPath());
			String fileName = utilisateur.getId() + ".png";
			Path path = Paths.get(Constants.USER_FOLDER + fileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mailSender.send(emailConstructor.constructNewUserEmail(utilisateur, password));
		return utilisateur;
	}

	@Override
	public void updateUserPassword(Utilisateur utilisateur, String newpassword) {
		String encryptedPassword = bCryptPasswordEncoder.encode(newpassword);
		utilisateur.setPassword(encryptedPassword);
		utilisateurRepository.save(utilisateur);
		mailSender.send(emailConstructor.constructResetPasswordEmail(utilisateur, newpassword));
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}


	@Override
	public Utilisateur findByUsername(String username) {
		return utilisateurRepository.findByUsername(username);
	}

	@Override
	public Utilisateur findByEmail(String userEmail) {
		return utilisateurRepository.findByEmail(userEmail);
	}

	@Override
	public List<Utilisateur> userList() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Role findUserRoleByName(String name) {
		return roleRepository.findRoleByRoleName(name);
	}

	@Override
	public Utilisateur simpleSaveUser(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
		mailSender.send(emailConstructor.constructUpdateUserProfileEmail(utilisateur));
		return utilisateur;

	}

	@Override
	public Utilisateur updateUser(Utilisateur utilisateur, HashMap<String, String> request) {
		String nom = request.get("nom");
		// String username = request.get("username");
		String email = request.get("email");
		String bio = request.get("bio");
		utilisateur.setNom(nom);
		// appUser.setUsername(username);
		utilisateur.setEmail(email);
		utilisateur.setBio(bio);
		utilisateurRepository.save(utilisateur);
		mailSender.send(emailConstructor.constructUpdateUserProfileEmail(utilisateur));
		return utilisateur;

	}

	@Override
	public Utilisateur findUserById(Long id) {
		return utilisateurRepository.findUserById(id);
	}

	@Override
	public void deleteUser(Utilisateur utilisateur) {
		utilisateurRepository.delete(utilisateur);

	}

	@Override
	public void resetPassword(Utilisateur utilisateur) {
		String password = RandomStringUtils.randomAlphanumeric(10);
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		utilisateur.setPassword(encryptedPassword);
		utilisateurRepository.save(utilisateur);
		mailSender.send(emailConstructor.constructResetPasswordEmail(utilisateur, password));

	}

	@Override
	public List<Utilisateur> getUsersListByUsername(String username) {
		return utilisateurRepository.findByUsernameContaining(username);
	}

	@Override
	public String saveUserImage(MultipartFile multipartFile, Long userImageId) {
		/*
		 * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
		 * request; Iterator<String> it = multipartRequest.getFileNames(); MultipartFile
		 * multipartFile = multipartRequest.getFile(it.next());
		 */
		byte[] bytes;
		try {
			Files.deleteIfExists(Paths.get(Constants.USER_FOLDER + "/" + userImageId + ".png"));
			bytes = multipartFile.getBytes();
			Path path = Paths.get(Constants.USER_FOLDER + userImageId + ".png");
			Files.write(path, bytes);
			return "User picture saved to server";
		} catch (IOException e) {
			return "User picture Saved";
		}
	}

}
