package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Categorie;
import GesBlio.bibliotheque.entities.Livre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategorieService {
    public Categorie add(Categorie categorie);
    public Categorie findById(Long idCategorie);
    public List<Categorie> categories();
    public Page<Categorie> categories(int pageNum, int pageSize);
    public Categorie update(Categorie categorie);
    public void delete(Long idCategorie);
}
