package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
