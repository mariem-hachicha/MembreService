package tn.enis.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor //Lombok, génère-moi automatiquement un constructeur sans paramètres
@Entity
@DiscriminatorValue("ens")

public class EnsignantChercheur extends Membre implements Serializable{

	private String grade;
	private String etablissement;
	@Builder
	public EnsignantChercheur(Long id, String cin, String nom, String prenom, LocalDate dateNaissance, byte[] photo,
			String Cv, String email, String password, String grade, String etablissement) {
		super(id, cin, nom, prenom, dateNaissance, photo, Cv, email, password);
		this.grade = grade;
		this.etablissement = etablissement;
	}



}
