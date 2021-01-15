package galerie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import galerie.entity.Tableau;

// This will be AUTO IMPLEMENTED by Spring 

public interface TableauRepository extends JpaRepository<Tableau, Integer> {

}
