package com.example.ApiTourist.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurRole implements Serializable {

	private static final long serialVersionUID = 164669782975869L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userRoleId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Utilisateur utilisateur;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	public UtilisateurRole(Utilisateur utilisateur, Role role) {

	}
}
