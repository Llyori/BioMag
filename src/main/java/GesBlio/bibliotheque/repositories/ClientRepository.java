package GesBlio.bibliotheque.repositories;

import GesBlio.bibliotheque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByPhoneNumber(String phoneNumber);
}
