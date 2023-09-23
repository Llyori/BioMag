package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Emprunt;
import GesBlio.bibliotheque.services.EmpruntService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/emprunt")
public class EmpruntController {
    private EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }
    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        return new ResponseEntity<>(empruntService.emprunts(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Emprunt> add(@RequestBody Emprunt emprunt){
        return new ResponseEntity<>(empruntService.add(emprunt), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Emprunt> update(@RequestBody Emprunt emprunt){
        return new ResponseEntity<>(empruntService.update(emprunt), HttpStatus.CREATED);
    }
    @GetMapping("/find/id/{idEmprunt}")
    public ResponseEntity<Emprunt> find(@Param("idEmprunt") Long idEmprunt){
        return new ResponseEntity<>(empruntService.findById(idEmprunt), HttpStatus.CREATED);
    }
    @GetMapping("delete/{idEmprunt}")
    public void delete(@Param("idEmprunt") Long idEmprunt){
        empruntService.delete(idEmprunt);
    }
    @GetMapping("/update/statut/{id}")
    public void updateStatut(@Param("id") Long id){
        empruntService.updateStatut(id);
    }
}
