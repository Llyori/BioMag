package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Emprunt;

import java.util.List;

public interface EmpruntService {
    public Emprunt add(Emprunt emprunt);
    public Emprunt findById(Long idEmprunt);
    public Emprunt update(Emprunt emprunt);
    public List<Emprunt> emprunts();
    public void delete(Long idEmprunt);
    public void updateStatut(Long idEmprunt);
}
