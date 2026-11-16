package tn.enis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enis.entities.EnsignantChercheur;

public interface EnseignantChercheurRepository
        extends JpaRepository<EnsignantChercheur, Long> {

    List<EnsignantChercheur> findByGrade(String grade);

    List<EnsignantChercheur> findByEtablissement(String etablissement);
}