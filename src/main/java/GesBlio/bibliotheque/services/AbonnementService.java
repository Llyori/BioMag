package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Abonnement;

import java.util.List;

public interface AbonnementService {
    public Abonnement add(Abonnement abonnement);
    public Abonnement findById(Long idAbonnement);
    public List<Abonnement> abonnements();
    public Abonnement update(Abonnement abonnement);
    public void delete(Long idAbonnement);
}
