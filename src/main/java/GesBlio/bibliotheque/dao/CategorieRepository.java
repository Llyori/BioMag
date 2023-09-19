package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
