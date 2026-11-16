
package tn.enis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import tn.enis.entities.Membre;
import tn.enis.entities.Etudiant;
import tn.enis.entities.EnsignantChercheur;

import tn.enis.dao.EnseignantChercheurRepository;
import tn.enis.dao.EtudiantRepository;
import tn.enis.dao.MembreRepository;

@Service
@AllArgsConstructor
public class MembreImpl implements IMembreService {

    private final MembreRepository membreRepository;

    private final EtudiantRepository etudiantRepository;

    private final EnseignantChercheurRepository enseignantChercheurRepository;

    // ==================================
    // CRUD SUR LES MEMBRES
    // ==================================

    @Override
    public Membre addMember(Membre m) {
        return membreRepository.save(m);
    }

    @Override
    public void deleteMember(Long id) {
        membreRepository.deleteById(id);
    }

    @Override
    public Membre updateMember(Membre m) {
        return membreRepository.saveAndFlush(m);
    }

    @Override
    public Membre findMember(Long id) {
        return membreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Membre> findAll() {
        return membreRepository.findAll();
    }

    // ==================================
    // FILTRAGE PAR PROPRIETE
    // ==================================

    @Override
    public Membre findByCin(String cin) {
        return membreRepository.findByCin(cin);
    }

    @Override
    public Membre findByEmail(String email) {
        return membreRepository.findByEmail(email);
    }

    @Override
    public List<Membre> findByNom(String nom) {
        return membreRepository.findByNomStartingWith(nom);
    }

    // ==================================
    // RECHERCHE DES ETUDIANTS
    // ==================================

    @Override
    public List<Etudiant> findByDiplome(String diplome) {
        return etudiantRepository.findByDiplome(diplome);
    }

    // ==================================
    // RECHERCHE DES ENSEIGNANTS
    // ==================================

    @Override
    public List<EnsignantChercheur> findByGrade(String grade) {
        return enseignantChercheurRepository.findByGrade(grade);
    }

    @Override
    public List<EnsignantChercheur> findByEtablissement(
            String etablissement) {

        return enseignantChercheurRepository
                .findByEtablissement(etablissement);
    }
    
    @Override
    public void affecterEtudiant(Long idEtudiant, Long idEnseignant) {

        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() ->
                        new IllegalArgumentException("Etudiant introuvable"));

        EnsignantChercheur enseignant =
                enseignantChercheurRepository.findById(idEnseignant)
                .orElseThrow(() ->
                        new IllegalArgumentException("Enseignant introuvable"));

        etudiant.setEncadrent(enseignant);

        etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> etudiantsParEnseignant(Long idEnseignant) {

        return etudiantRepository.findByEncadrentId(idEnseignant);
    }
}
