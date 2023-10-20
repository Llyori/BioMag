package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Livre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LivreService {
    public Livre add(Livre livre);
    public Livre findById(Long idLivre);
    public Livre update(Livre livre);
    public Page<Livre> livres(int pageNum, int pageSize);
    public List<Livre> livres();
    public void delete(Long idLivre);
    public void activerLivre(Long idLivre);
    public void desactiverLivre(Long idLivre);
}
