package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Livre;
import GesBlio.bibliotheque.services.CategorieService;
import GesBlio.bibliotheque.services.LivreService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livre")
public class LivreController {
    private LivreService livreService;
    private CategorieService categorieService;

    public LivreController(LivreService livreService, CategorieService categorieService) {
        this.livreService = livreService;
        this.categorieService = categorieService;
    }
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Livre> livrePage = livreService.livres(page, 10);
        model.addAttribute("livres", livrePage);
        model.addAttribute("categories", categorieService.categories());
        model.addAttribute("livre", new Livre());
        return "livre/list";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("livre") Livre livre){
        Livre l = livreService.add(livre);
        return "redirect:/livre/list";
    }
    @GetMapping("/find/id/idLivre")
    public ResponseEntity<Livre> find(@Param("idLivre") Long idLivre){
        return new ResponseEntity<>(livreService.findById(idLivre), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Livre> update(@RequestBody Livre livre){
        return new ResponseEntity<>(livreService.update(livre), HttpStatus.ACCEPTED);
    }
    @GetMapping("delete/{idLivre}")
    public void delete(@Param("idLivre") Long idLivre){
        livreService.delete(idLivre);
    }
    @GetMapping("activer/{idLivre}")
    public void activerLivre(@Param("idLivre") Long idLivre){
        livreService.activerLivre(idLivre);
    }
    @GetMapping("desactiver/{idLivre}")
    public void desactiver(@Param("idLivre") Long idLivre){
        livreService.desactiverLivre(idLivre);
    }
}
