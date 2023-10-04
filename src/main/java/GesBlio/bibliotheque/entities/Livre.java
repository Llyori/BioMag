package GesBlio.bibliotheque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;
    private String nomLivre;
    private String auteur;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datePublication;
    private double prixJournalier;
    private int nbreOccurence;
    @ManyToOne
    @JoinColumn(name = "categorie")
    private Categorie categorie;
    @OneToMany(mappedBy = "livre")
    private List<Emprunt> emprunt;
    private boolean actif; // Si le statut actif est true, alors le livre peut être emprunté sinon, il pourra juste être lu en bibliothèque
    @Column(columnDefinition = "boolean default true")
    private boolean statut; // Lorsqu'on veut supprimer un livre et ne plus pouvoir donner l'occasion qu'il soit emprunté, le statut passe à false
}
