package galerie.entity;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Exposition {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private String intitule;
    
    private LocalDate debut = LocalDate.now();    
    
    private Integer duree = 10;
    
    @ManyToOne
    @NonNull
    Galerie organisateur;

    @ManyToMany
    @ToString.Exclude
    List<Tableau> oeuvres = new LinkedList<>();
    
    @OneToMany(mappedBy = "lieuDeVente")
    private List<Transaction> ventes = new LinkedList<>();
    
    public float CA() {
        // Une implémentation en utilisant l'API Stream API.
        // cf. https://www.baeldung.com/java-stream-sum
        return ventes.stream().map(v -> v.getPrixVente()).reduce(0f, Float::sum);
    }

}
