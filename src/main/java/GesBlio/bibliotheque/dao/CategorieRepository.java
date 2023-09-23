package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    @Query("UPDATE Categorie c SET c.statut = false WHERE c.id = ?1")
    @Modifying
    public void desable(Long id);
}
