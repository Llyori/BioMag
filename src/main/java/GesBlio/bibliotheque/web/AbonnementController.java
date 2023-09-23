package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Abonnement;
import GesBlio.bibliotheque.services.AbonnementService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/abonnement")
public class AbonnementController {
    private AbonnementService abonnementService;

    public AbonnementController(AbonnementService abonnementService) {
        this.abonnementService = abonnementService;
    }
    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        return new ResponseEntity<>(abonnementService.abonnements(), HttpStatus.ACCEPTED);
    }
    @PostMapping("/add")
    public ResponseEntity<Abonnement> add(@RequestBody Abonnement abonnement){
        return new ResponseEntity<>(abonnementService.add(abonnement), HttpStatus.CREATED);
    }
    @GetMapping("/find/{idAbonnement}")
    public ResponseEntity<Abonnement> find(@Param("idAbonnement") Long idAbonnement){
        return new ResponseEntity<>(abonnementService.findById(idAbonnement), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Abonnement> update(@RequestBody Abonnement abonnement){
        return new ResponseEntity<>(abonnementService.update(abonnement), HttpStatus.OK);
    }
    @GetMapping("/delete/{idAbonnement}")
    public void delete(@Param("idAbonnement") Long idAbonnement){
        abonnementService.delete(idAbonnement);
    }
}
