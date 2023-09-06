package GesBlio.bibliotheque.repositories;

import GesBlio.bibliotheque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByPhoneNumber(String phoneNumber);
    @Query("UPDATE Client c SET c.enabled = true WHERE c.id = ?1")
    @Modifying
    public void enable(Long id);
    public Client findByCodeVerification(String codeVerification);
}
