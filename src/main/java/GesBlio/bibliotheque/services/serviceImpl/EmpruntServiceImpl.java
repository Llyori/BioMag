package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.dao.EmpruntRepository;
import GesBlio.bibliotheque.entities.Emprunt;
import GesBlio.bibliotheque.services.EmpruntService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpruntServiceImpl implements EmpruntService {
    private EmpruntRepository empruntRepository;

    public EmpruntServiceImpl(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    @Override
    public Emprunt add(Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    @Override
    public Emprunt findById(Long idEmprunt) {
        return empruntRepository.findById(idEmprunt).get();
    }

    @Override
    public Emprunt update(Emprunt emprunt) {
        Emprunt emprunt1 = empruntRepository.findById(emprunt.getIdEmprunt()).get();
        emprunt1 = emprunt;
        return empruntRepository.save(emprunt1);
    }

    @Override
    public List<Emprunt> emprunts() {
        return empruntRepository.findAll();
    }

    @Override
    public void delete(Long idEmprunt) {
        empruntRepository.deleteById(idEmprunt);
    }

    @Override
    public void updateStatut(Long idEmprunt) {
        empruntRepository.updateStatut(idEmprunt);
    }
}
