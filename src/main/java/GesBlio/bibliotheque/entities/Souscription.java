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
    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "abonnement")
    private Abonnement abonnement;
    @Column(columnDefinition = "boolean default false")
    private boolean actif; // Lorsqu'une souscription est crée, elle reste false, c'est après payement qu'il passe à true
    @Column(columnDefinition = "boolean default true")
    private boolean statut; // Si on supprime ou suspend une souscription, son statut passe juste a false
}
