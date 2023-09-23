package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    @Query("UPDATE Emprunt e SET e.enabled = false WHERE e.id = ?1")
    @Modifying
    public void desable(Long id);
    @Query("UPDATE Emprunt e SET e.statut = true WHERE e.id = ?1")
    @Modifying
    public void updateStatut(Long id);
}
