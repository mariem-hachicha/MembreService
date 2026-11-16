package tn.enis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enis.entities.Etudiant;

public interface EtudiantRepository
        extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByDiplome(String diplome);

    List<Etudiant> findByDiplomeOrderByDateDescriptionDesc(
            String diplome);
    
    List<Etudiant> findByEncadrentId(Long idEnseignant);
}