package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Abonnement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AbonnementService {
    public Abonnement add(Abonnement abonnement);
    public Abonnement findById(Long idAbonnement);
    public Page<Abonnement> abonnements(int pageNum, int pageSize);
    public List<Abonnement> abonnements();
    public Abonnement update(Abonnement abonnement);
    public void delete(Long idAbonnement);
}
