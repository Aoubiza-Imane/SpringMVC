package galerie.dao;

import galerie.entity.Galerie;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.context.annotation.Profile;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class GalerieRepositoryTest {

    @Autowired
    private GalerieRepository galerieDAO;

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Galerie'");
        int combienDansLeJeuDeTest = 3; // 2 dans data.sql, 1 dans test-data.sql
        long nombre = galerieDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 3 galeries" );
    }
    
    @Test // On utilise les données de 'data.sql' qui sont pré-chargées
    public void onSaitCalculerLeCAAnnuel() {
        LocalDate now = LocalDate.now();
        Galerie saatchi = galerieDAO.findById(1).orElseThrow();
        log.info("On calcule le CA de {} pour l'année {}", saatchi.getNom(), now.getYear());
        float result = saatchi.CAannuel(now.getYear());
        assertEquals(6000.06f, result, 0.001f, 
                "Le CA de cette galerie est de 6000.06f" );
    }
}
