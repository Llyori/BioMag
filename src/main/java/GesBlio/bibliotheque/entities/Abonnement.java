package GesBlio.bibliotheque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAbonnement;
    private String nomAbonnement;
    private String description;
    private int nbreLivre;
    private int periode;
    private double montant;
    private String statut;
    @Transient
    private int nbreUtilisateur;
    @OneToMany(mappedBy = "abonnement")
    private List<Souscription> souscription;
}
