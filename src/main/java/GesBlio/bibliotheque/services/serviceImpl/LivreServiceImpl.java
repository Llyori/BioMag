package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.dao.LivreRepository;
import GesBlio.bibliotheque.entities.Livre;
import GesBlio.bibliotheque.services.LivreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements LivreService {
    private LivreRepository livreRepository;

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    public Livre add(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public Livre findById(Long idLivre) {
        return livreRepository.findById(idLivre).get();
    }

    @Override
    public Livre update(Livre livre) {
        Livre livre1 = livreRepository.findById(livre.getIdLivre()).get();
        livre1 = livre;
        return livreRepository.save(livre1);
    }

    @Override
    public List<Livre> livres() {
        return livreRepository.findAll();
    }

    @Override
    public void delete(Long idLivre) {
        livreRepository.deleteById(idLivre);
    }
}
