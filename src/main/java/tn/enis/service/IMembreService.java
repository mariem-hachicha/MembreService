
package tn.enis.service;

import java.util.List;

import tn.enis.entities.Membre;
import tn.enis.entities.Etudiant;
import tn.enis.entities.EnsignantChercheur;

public interface IMembreService {

    // CRUD sur les membres
    public Membre addMember(Membre m);

    public void deleteMember(Long id);

    public Membre updateMember(Membre m);

    public Membre findMember(Long id);

    public List<Membre> findAll();

    // Filtrage par propriété
    public Membre findByCin(String cin);

    public Membre findByEmail(String email);

    public List<Membre> findByNom(String nom);

    // Recherche spécifique des étudiants
    public List<Etudiant> findByDiplome(String diplome);

    // Recherche spécifique des enseignants
    public List<EnsignantChercheur> findByGrade(String grade);

    public List<EnsignantChercheur> findByEtablissement(
            String etablissement);
    
    void affecterEtudiant(Long idEtudiant, Long idEnseignant);

    List<Etudiant> etudiantsParEnseignant(Long idEnseignant);
}
