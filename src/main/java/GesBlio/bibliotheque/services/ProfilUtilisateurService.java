package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Categorie;
import GesBlio.bibliotheque.entities.ProfilUtilisateur;

import java.util.List;

public interface ProfilUtilisateurService {
    public void addProfilToUser(ProfilUtilisateur profilUtilisateur);
    public void addCategoriesToProfilUser(Long idClient, List<Categorie> categories);
}
