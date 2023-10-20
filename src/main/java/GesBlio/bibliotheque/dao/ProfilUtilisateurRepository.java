package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.entities.ProfilUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilUtilisateurRepository extends JpaRepository<ProfilUtilisateur, Long> {
    ProfilUtilisateur findByClient(Client client);
}
