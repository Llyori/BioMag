package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    @Query("UPDATE Livre l SET l.actif = false WHERE l.id = ?1")
    @Modifying
    public void desactiver(Long id);
    @Query("UPDATE Livre l SET l.actif = true WHERE l.id = ?1")
    @Modifying
    public void activer(Long id);
    @Query("UPDATE Livre l SET l.statut = false WHERE l.id = ?1")
    @Modifying
    public void move(Long id);
}
