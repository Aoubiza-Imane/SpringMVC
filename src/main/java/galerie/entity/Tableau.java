package galerie.entity;
import java.util.*;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Tableau {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private String titre;

    private String support;
    
    private String dimension;

    @OneToOne(mappedBy = "oeuvre")
    @ToString.Exclude
    private Transaction vendu;    
    
    @ManyToOne
    // Peut-être null
    Artiste auteur;

    @ToString.Exclude
    @ManyToMany(mappedBy= "oeuvres")
    private List<Exposition> accrochages = new LinkedList<>();
    
}
