
package tn.enis.controlleur;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

import tn.enis.entities.Membre;
import tn.enis.entities.Etudiant;
import tn.enis.entities.EnsignantChercheur;

import tn.enis.service.IMembreService;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/membres")
public class MembreRestController {

    private final IMembreService membreService;

    // ==========================================
    // 1. AFFICHER TOUS LES MEMBRES
    // GET /membres
    // ==========================================

    @GetMapping
    public List<Membre> findMembres() {
        return membreService.findAll();
    }

    // ==========================================
    // 2. CHERCHER UN MEMBRE PAR ID
    // GET /membres/1
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Membre> findOneMemberById(
            @PathVariable("id") Long id) {

        Membre membre = membreService.findMember(id);

        if (membre == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(membre);
    }

    // ==========================================
    // 3. CHERCHER PAR CIN
    // GET /membres/search/cin?cin=12345678
    // ==========================================

    @GetMapping("/search/cin")
    public ResponseEntity<Membre> findOneMemberByCin(
            @RequestParam("cin") String cin) {

        Membre membre = membreService.findByCin(cin);

        if (membre == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(membre);
    }

    // ==========================================
    // 4. CHERCHER PAR EMAIL
    // GET /membres/search/email?email=ali@test.com
    // ==========================================

    @GetMapping("/search/email")
    public ResponseEntity<Membre> findOneMemberByEmail(
            @RequestParam("email") String email) {

        Membre membre = membreService.findByEmail(email);

        if (membre == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(membre);
    }

    // ==========================================
    // 5. CHERCHER PAR NOM
    // GET /membres/search/nom?nom=Ali
    // ==========================================

    @GetMapping("/search/nom")
    public List<Membre> findMembersByNom(
            @RequestParam("nom") String nom) {

        return membreService.findByNom(nom);
    }

    // ==========================================
    // 6. CHERCHER LES ETUDIANTS PAR DIPLOME
    // GET /membres/search/diplome?diplome=Ingenieur
    // ==========================================

    @GetMapping("/search/diplome")
    public List<Etudiant> findStudentsByDiplome(
            @RequestParam("diplome") String diplome) {

        return membreService.findByDiplome(diplome);
    }

    // ==========================================
    // 7. CHERCHER LES ENSEIGNANTS PAR GRADE
    // GET /membres/search/grade?grade=Professeur
    // ==========================================

    @GetMapping("/search/grade")
    public List<EnsignantChercheur> findTeachersByGrade(
            @RequestParam("grade") String grade) {

        return membreService.findByGrade(grade);
    }

    // ==========================================
    // 8. CHERCHER PAR ETABLISSEMENT
    // GET /membres/search/etablissement?etablissement=ENIS
    // ==========================================

    @GetMapping("/search/etablissement")
    public List<EnsignantChercheur> findTeachersByEtablissement(
            @RequestParam("etablissement") String etablissement) {

        return membreService.findByEtablissement(etablissement);
    }

    // ==========================================
    // 9. AJOUTER UN ENSEIGNANT
    // POST /membres/enseignant
    // ==========================================

    @PostMapping("/enseignant")
    public Membre addEnseignant(
            @RequestBody EnsignantChercheur enseignant) {

        return membreService.addMember(enseignant);
    }

    // ==========================================
    // 10. AJOUTER UN ETUDIANT
    // POST /membres/etudiant
    // ==========================================

    @PostMapping("/etudiant")
    public Membre addEtudiant(
            @RequestBody Etudiant etudiant) {

        return membreService.addMember(etudiant);
    }

    // ==========================================
    // 11. MODIFIER UN ETUDIANT
    // PUT /membres/etudiant/1
    // ==========================================

    @PutMapping("/etudiant/{id}")
    public ResponseEntity<Membre> updateEtudiant(
            @PathVariable("id") Long id,
            @RequestBody Etudiant etudiant) {

        Membre membre = membreService.findMember(id);

        if (!(membre instanceof Etudiant)) {
            return ResponseEntity.notFound().build();
        }

        etudiant.setId(id);

        return ResponseEntity.ok(
                membreService.updateMember(etudiant)
        );
    }

    // ==========================================
    // 12. MODIFIER UN ENSEIGNANT
    // PUT /membres/enseignant/3
    // ==========================================

    @PutMapping("/enseignant/{id}")
    public ResponseEntity<Membre> updateEnseignant(
            @PathVariable("id") Long id,
            @RequestBody EnsignantChercheur enseignant) {

        Membre membre = membreService.findMember(id);

        if (!(membre instanceof EnsignantChercheur)) {
            return ResponseEntity.notFound().build();
        }

        enseignant.setId(id);

        return ResponseEntity.ok(
                membreService.updateMember(enseignant)
        );
    }

    // ==========================================
    // 13. SUPPRIMER UN MEMBRE
    // DELETE /membres/2
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembre(
            @PathVariable("id") Long id) {

        Membre membre = membreService.findMember(id);

        if (membre == null) {
            return ResponseEntity.notFound().build();
        }

        membreService.deleteMember(id);

        return ResponseEntity.noContent().build();
    }

    // ==========================================
    // 14. AFFECTER UN ETUDIANT A UN ENSEIGNANT
    // PUT /membres/etudiant/1/enseignant/3
    // ==========================================

    @PutMapping("/etudiant/{idEtudiant}/enseignant/{idEnseignant}")
    public ResponseEntity<String> affecterEtudiant(
            @PathVariable Long idEtudiant,
            @PathVariable Long idEnseignant) {

        Membre etudiant = membreService.findMember(idEtudiant);
        Membre enseignant = membreService.findMember(idEnseignant);

        if (!(etudiant instanceof Etudiant)
                || !(enseignant instanceof EnsignantChercheur)) {

            return ResponseEntity.notFound().build();
        }

        membreService.affecterEtudiant(
                idEtudiant, idEnseignant);

        return ResponseEntity.ok(
                "Etudiant affecté avec succès !");
    }

    // ==========================================
    // 15. ETUDIANTS ENCADRES PAR UN ENSEIGNANT
    // GET /membres/enseignant/3/etudiants
    // ==========================================

    @GetMapping("/enseignant/{id}/etudiants")
    public List<Etudiant> etudiantsParEnseignant(
            @PathVariable("id") Long id) {

        return membreService.etudiantsParEnseignant(id);
    }
}
