package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
}
