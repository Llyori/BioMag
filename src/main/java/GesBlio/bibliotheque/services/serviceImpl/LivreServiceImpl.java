package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.dao.LivreRepository;
import GesBlio.bibliotheque.entities.Livre;
import GesBlio.bibliotheque.services.LivreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Livre> livres(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return livreRepository.findAll(pageable);
    }

    @Override
    public void delete(Long idLivre) {
        livreRepository.move(idLivre);
    }

    @Override
    public void activerLivre(Long idLivre) {
        livreRepository.activer(idLivre);
    }

    @Override
    public void desactiverLivre(Long idLivre) {
        livreRepository.desactiver(idLivre);
    }
}
