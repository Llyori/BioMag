package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.entities.Souscription;

import java.util.List;

public interface SouscriptionService {
    public Souscription add(Souscription souscription);
    public Souscription findById(Long idSouscription);
    public Souscription update(Souscription souscription);
    public List<Souscription> souscriptions();
    public void delete(Long idSouscription);
    public void activerSouscription(Long idSouscription); // Après règlement des frais financiers, la souscription peut-être activée
    List<Souscription> findAllByClient(Client client);
}
