package galerie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import galerie.entity.Artiste;

// This will be AUTO IMPLEMENTED by Spring 

public interface ArtisteRepository extends JpaRepository<Artiste, Integer> {

}
