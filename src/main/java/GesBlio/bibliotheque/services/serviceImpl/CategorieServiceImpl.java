package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.dao.CategorieRepository;
import GesBlio.bibliotheque.entities.Categorie;
import GesBlio.bibliotheque.services.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie add(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie findById(Long idCategorie) {
        return categorieRepository.findById(idCategorie).get();
    }

    @Override
    public List<Categorie> categories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie update(Categorie categorie) {
        Categorie categorie1 = categorieRepository.findById(categorie.getIdCategorie()).get();
        categorie1 = categorie;
        return categorieRepository.save(categorie1);
    }

    @Override
    public void delete(Long idCategorie) {
        categorieRepository.deleteById(idCategorie);
    }
}
