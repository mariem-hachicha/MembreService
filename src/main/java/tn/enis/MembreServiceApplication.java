
package tn.enis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

import tn.enis.entities.Membre;
import tn.enis.entities.Etudiant;
import tn.enis.entities.EnsignantChercheur;
import tn.enis.service.IMembreService;

@SpringBootApplication
@AllArgsConstructor
public class MembreServiceApplication implements CommandLineRunner {

    private final IMembreService membreService;

    public static void main(String[] args) {
        SpringApplication.run(MembreServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // =========================================
        // 1. AFFICHER TOUS LES MEMBRES
        // =========================================

        System.out.println("=== LISTE INITIALE ===");

        membreService.findAll().forEach(m -> {
            System.out.println(
                m.getId() + " - " + m.getNom()
            );
        });

        // =========================================
        // 2. MODIFIER LE CV DU MEMBRE ID = 1
        // =========================================

        System.out.println("=== MODIFICATION ===");

        Membre m = membreService.findMember(1L);

        if (m != null) {

            m.setCv("cv1.pdf");

            membreService.updateMember(m);

            System.out.println(
                "CV modifié pour : " + m.getNom()
            );

        } else {

            System.out.println(
                "Membre ID 1 introuvable !"
            );
        }

        // =========================================
        // 3. SUPPRIMER LE MEMBRE ID = 2
        // =========================================

        System.out.println("=== SUPPRESSION ===");

        Membre membreASupprimer =
                membreService.findMember(2L);

        if (membreASupprimer != null) {

            membreService.deleteMember(2L);

            System.out.println(
                "Membre ID 2 supprimé !"
            );

        } else {

            System.out.println(
                "Membre ID 2 introuvable !"
            );
        }

        // =========================================
        // 4. AFFICHER LA LISTE FINALE
        // =========================================

        System.out.println("=== LISTE FINALE ===");

        membreService.findAll().forEach(membre -> {

            System.out.println(
                membre.getId() + " - " +
                membre.getNom() + " - CV : " +
                membre.getCv()
            );

        });

        // =========================================
        // 5. AFFICHER UNIQUEMENT LES ETUDIANTS
        // =========================================

        System.out.println("=== ETUDIANTS ===");

        for (Membre membre : membreService.findAll()) {

            if (membre instanceof Etudiant) {

                System.out.println(
                    membre.getId() + " - " +
                    membre.getNom()
                );

            }
        }

        // =========================================
        // 6. AFFICHER UNIQUEMENT LES ENSEIGNANTS
        // =========================================

        System.out.println("=== ENSEIGNANTS ===");

        for (Membre membre : membreService.findAll()) {

            if (membre instanceof EnsignantChercheur) {

                System.out.println(
                    membre.getId() + " - " +
                    membre.getNom()
                );

            }
        }
        System.out.println("=== EXERCICE 34 ===");

        // 1. Affecter Ali à Ahmed
        membreService.affecterEtudiant(1L, 3L);

        System.out.println("Ali affecté à Ahmed !");

        // 2. Affecter Sara à Ahmed
        membreService.affecterEtudiant(6L, 3L);

        System.out.println("Sara affectée à Ahmed !");

        // 3. Afficher les étudiants encadrés par Ahmed
        System.out.println("=== ETUDIANTS ENCADRES PAR AHMED ===");

        membreService.etudiantsParEnseignant(3L)
            .forEach(e -> {
                System.out.println(
                    e.getId() + " - " + e.getNom()
                );
            });

    }
}
