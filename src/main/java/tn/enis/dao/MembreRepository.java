package tn.enis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enis.entities.Membre;

public interface MembreRepository extends JpaRepository<Membre, Long> {

    Membre findByCin(String cin);

    List<Membre> findByNomStartingWith(String caractere); // mech ilawej par nom les nom ili bech yebdew b chaine illi erminha fi parmétre

    Membre findByEmail(String email);
}