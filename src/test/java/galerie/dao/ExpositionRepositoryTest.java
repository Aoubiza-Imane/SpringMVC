package galerie.dao;

import galerie.entity.Exposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.context.jdbc.Sql;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class ExpositionRepositoryTest {

    @Autowired
    private ExpositionRepository expositionDAO;

    @Test // On utilise les données de 'data.sql' qui sont pré-chargées
    public void onSaitCalculerLeCADuneExpositionEnJava() {
        Exposition painters = expositionDAO.findById(1).orElseThrow();
        log.info("On calcule le CA de l'exposition {} en java", painters.getIntitule());
        assertEquals(3000.03f, painters.CA(), 0.001f, 
                "Le CA de cette exposition est de 3000.03f" );
    }

    @Test // On utilise les données de 'data.sql' qui sont pré-chargées
    public void onSaitCalculerLeCADuneExpositionEnJPQL() {
        int idExposition = 1;
        log.info("On calcule le CA de l'exposition {} en JPQL", idExposition);
        assertEquals(3000.03f, expositionDAO.chiffreAffairePour(idExposition), 0.001f, 
                "Le CA de cette exposition est de 3000.03f" );
    }
    
    @Test // On utilise les données de 'data.sql' qui sont pré-chargées
    public void onSaitCalculerLeCADuneExpositionAvecJointure() {
        String intitule = "Painters' painters";
        log.info("On calcule le CA de l'exposition {} en JPQL", intitule);
        assertEquals(3000.03f, expositionDAO.chiffreAffairePour(intitule), 0.001f, 
                "Le CA de cette exposition est de 3000.03f" );
    }

    @Test // On utilise les données de 'data.sql' qui sont pré-chargées
    public void renvoieNullSiPasDEnregistrement() {
        int idExposition = 999;
        log.info("On calcule le CA de l'exposition {} en JPQL", idExposition);
        assertNull(expositionDAO.chiffreAffairePour(idExposition),
            "Cette expo n'existe pas, le résultat est null, et non pas 0.0f" );
    }

}
