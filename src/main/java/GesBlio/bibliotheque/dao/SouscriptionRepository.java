package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SouscriptionRepository extends JpaRepository<Souscription, Long> {
}
