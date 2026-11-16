package tn.enis.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //Lombok, génère-moi automatiquement un constructeur qui prend tous les attributs de ma classe. »
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //heya ta3mlik tableau single table maghir redondance des données w tzidek coulans par exmple type
@DiscriminatorColumn(name= "type_mbr", discriminatorType =
DiscriminatorType.STRING,length = 3)
public abstract class Membre implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cin;
	private String nom;
	private String prenom;


	private LocalDate dateNaissance;
	private byte[] photo;
	private String Cv;
	private String email;
	private String password;


}
