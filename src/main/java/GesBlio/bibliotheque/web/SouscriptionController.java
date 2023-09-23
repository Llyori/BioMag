package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Souscription;
import GesBlio.bibliotheque.services.ClientService;
import GesBlio.bibliotheque.services.SouscriptionService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/souscription")
public class SouscriptionController {
    private SouscriptionService souscriptionService;
    private ClientService clientService;

    public SouscriptionController(SouscriptionService souscriptionService, ClientService clientService) {
        this.souscriptionService = souscriptionService;
        this.clientService = clientService;
    }
    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        return new ResponseEntity<>(souscriptionService.souscriptions(), HttpStatus.OK);
    }
    @GetMapping("/list/{idClient}")
    public ResponseEntity<Object> listByClient(@Param("idClient") Long idClient){
        return new ResponseEntity<>(souscriptionService.findAllByClient(clientService.findById(idClient)), HttpStatus.OK);
    }
    @GetMapping("/find/id/{idSouscription}")
    public ResponseEntity<Souscription> find(@Param("idSouscription") Long idSouscription){
        return new ResponseEntity<>(souscriptionService.findById(idSouscription), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Souscription> add(@RequestBody Souscription souscription){
        return new ResponseEntity<>(souscriptionService.add(souscription), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Souscription> update(@RequestBody Souscription souscription){
        return new ResponseEntity<>(souscriptionService.update(souscription), HttpStatus.CREATED);
    }
    @GetMapping("/activer/{idSouscription}")
    public void activer(@Param("idSouscription") Long idSouscription){
        souscriptionService.activerSouscription(idSouscription);
    }
    @GetMapping("/suspendre/{idSouscription}")
    public void suspendre(@Param("idSouscription") Long idSouscription){
        souscriptionService.delete(idSouscription);
    }
}
