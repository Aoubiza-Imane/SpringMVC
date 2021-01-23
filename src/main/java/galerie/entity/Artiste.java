package galerie.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Artiste extends Personne {
    private String biographie;
    
    @OneToMany(mappedBy="auteur")
    @ToString.Exclude // pour ne pas avoir toute la liste d'infos
    private List<Tableau> oeuvres = new LinkedList<>();
}
