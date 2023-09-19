package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
