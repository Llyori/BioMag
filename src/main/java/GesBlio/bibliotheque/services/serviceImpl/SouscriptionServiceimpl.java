package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.dao.SouscriptionRepository;
import GesBlio.bibliotheque.entities.Souscription;
import GesBlio.bibliotheque.services.SouscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SouscriptionServiceimpl implements SouscriptionService {
    private SouscriptionRepository souscriptionRepository;

    public SouscriptionServiceimpl(SouscriptionRepository souscriptionRepository) {
        this.souscriptionRepository = souscriptionRepository;
    }

    @Override
    public Souscription add(Souscription souscription) {
        return souscriptionRepository.save(souscription);
    }

    @Override
    public Souscription findById(Long idSouscription) {
        return souscriptionRepository.findById(idSouscription).get();
    }

    @Override
    public Souscription update(Souscription souscription) {
        Souscription souscription1 = souscriptionRepository.findById(souscription.getIdSouscription()).get();
        souscription1 = souscription;
        return souscriptionRepository.save(souscription1);
    }

    @Override
    public List<Souscription> souscriptions() {
        return souscriptionRepository.findAll();
    }

    @Override
    public void delete(Long idSouscription) {
        souscriptionRepository.deleteById(idSouscription);
    }
}
