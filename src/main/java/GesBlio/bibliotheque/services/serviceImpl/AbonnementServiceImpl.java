package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.dao.AbonnementRepository;
import GesBlio.bibliotheque.entities.Abonnement;
import GesBlio.bibliotheque.services.AbonnementService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AbonnementServiceImpl implements AbonnementService {
    private AbonnementRepository abonnementRepository;

    public AbonnementServiceImpl(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    @Override
    public Abonnement add(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public Abonnement findById(Long idAbonnement) {
        return abonnementRepository.findById(idAbonnement).get();
    }

    @Override
    public List<Abonnement> abonnements() {
        return abonnementRepository.findAll();
    }

    @Override
    public Abonnement update(Abonnement abonnement) {
        Abonnement abonnement1 = abonnementRepository.findById(abonnement.getIdAbonnement()).get();
        abonnement1 = abonnement;
        return abonnementRepository.save(abonnement1);
    }

    @Override
    public void delete(Long idAbonnement) {
        abonnementRepository.deleteById(idAbonnement);
    }
}
