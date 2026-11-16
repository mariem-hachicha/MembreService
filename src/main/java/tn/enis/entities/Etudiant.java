package tn.enis.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
//Lombok, génère-moi automatiquement un constructeur sans paramètres
@DiscriminatorValue("etd")
@Entity
public class Etudiant extends Membre implements Serializable{
	private LocalDate dateDescription;
	private String diplome;

	@ManyToOne
	private EnsignantChercheur encadrent;
	@Builder
	public Etudiant(Long id, String cin, String nom, String prenom, LocalDate dateNaissance, byte[] photo, String Cv,
			String email, String password, LocalDate dateDescription, String diplome, EnsignantChercheur encadrent) {
		super(id, cin, nom, prenom, dateNaissance, photo, Cv, email, password);
		this.dateDescription = dateDescription;
		this.diplome = diplome;
		this.encadrent = encadrent;
	}
	//builder c est une méthode tekhalina nermiw paramétre mouch men4min kima constructor



}
