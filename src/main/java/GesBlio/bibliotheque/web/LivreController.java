package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Livre;
import GesBlio.bibliotheque.services.LivreService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/livre")
public class LivreController {
    private LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }
    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        return new ResponseEntity<>(livreService.livres(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Livre> add(@RequestBody Livre livre){
        return new ResponseEntity<>(livreService.add(livre), HttpStatus.CREATED);
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
