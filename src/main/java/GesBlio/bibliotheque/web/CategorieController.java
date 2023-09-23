package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Categorie;
import GesBlio.bibliotheque.services.CategorieService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/categorie")
public class CategorieController {
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        return new ResponseEntity<>(categorieService.categories(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Categorie> add(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categorieService.add(categorie), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Categorie> update(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categorieService.update(categorie), HttpStatus.ACCEPTED);
    }
    @GetMapping("/find/id/{idCategorie}")
    public ResponseEntity<Categorie> find(@Param("idCategorie") Long idCategorie){
        return new ResponseEntity<>(categorieService.findById(idCategorie), HttpStatus.OK);
    }
    @GetMapping("/delete/{idCategorie}")
    public void delete(@Param("idCategorie") Long idCategorie){
        categorieService.delete(idCategorie);
    }
}
