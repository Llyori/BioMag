package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Categorie;

import java.util.List;

public interface CategorieService {
    public Categorie add(Categorie categorie);
    public Categorie findById(Long idCategorie);
    public List<Categorie> categories();
    public Categorie update(Categorie categorie);
    public void delete(Long idCategorie);
}
