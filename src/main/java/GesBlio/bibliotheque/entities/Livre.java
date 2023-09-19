package GesBlio.bibliotheque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Date datePublication;
    private double prixJournalier;
    private int nbreOccurence;
    @ManyToOne
    @JoinColumn(name = "categorie")
    private Categorie categorie;
    @OneToMany(mappedBy = "livre")
    private List<Emprunt> emprunt;
}
