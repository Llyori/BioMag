package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Categorie;
import GesBlio.bibliotheque.entities.Livre;
import GesBlio.bibliotheque.services.CategorieService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/categorie")
public class CategorieController {
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Categorie> categories = categorieService.categories(page, 10);
        model.addAttribute("categories", categories);
        model.addAttribute("categorie", new Categorie());
        return "categorie/list";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("categorie") Categorie categorie){
        Categorie c = categorieService.add(categorie);
        return "redirect:/categorie/list";
    }
    @PutMapping("/update")
    public ResponseEntity<Categorie> update(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categorieService.update(categorie), HttpStatus.ACCEPTED);
    }
    @GetMapping("/find/id/{idCategorie}")
    public ResponseEntity<Categorie> find(@Param("idCategorie") Long idCategorie){
        return new ResponseEntity<>(categorieService.findById(idCategorie), HttpStatus.OK);
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("idCategorie") Long idCategorie){
        categorieService.delete(idCategorie);
        return "redirect:/categorie/list";
    }
}
