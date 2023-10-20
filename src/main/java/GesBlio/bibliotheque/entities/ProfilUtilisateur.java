package GesBlio.bibliotheque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ProfilUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfilUtilisateur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client")
    private Client client;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categorie> categories = new ArrayList<>();

    public ProfilUtilisateur(Client client){
        this.client = client;
    }
}
