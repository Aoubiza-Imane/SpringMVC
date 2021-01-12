package galerie.entity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Transaction {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    @ManyToOne
    private Personne client;
   
    @NonNull
    @OneToOne
    private Tableau oeuvre;
    
    @NonNull
    @ManyToOne
    private Exposition lieuDeVente;
    
    @NonNull
    private Float prixVente;
    
    private LocalDate venduLe = LocalDate.now();
    
}
