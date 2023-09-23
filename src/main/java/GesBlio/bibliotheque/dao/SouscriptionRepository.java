package GesBlio.bibliotheque.dao;

import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.entities.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SouscriptionRepository extends JpaRepository<Souscription, Long> {
    @Query("UPDATE Souscription s SET s.statut = false WHERE s.id = ?1")
    @Modifying
    public void suspendre(Long id);
    @Query("UPDATE Souscription s SET s.actif = true WHERE s.id = ?1")
    @Modifying
    public void activer(Long id);
    List<Souscription> findAllByClient(Client client);
}
