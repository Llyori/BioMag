package GesBlio.bibliotheque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Souscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSouscription;
    private Date dateDebut;
    private String statut;
    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "abonnement")
    private Abonnement abonnement;
}
