package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.entities.Abonnement;
import GesBlio.bibliotheque.services.AbonnementService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/abonnement")
public class AbonnementController {
    private AbonnementService abonnementService;

    public AbonnementController(AbonnementService abonnementService) {
        this.abonnementService = abonnementService;
    }
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page){
        Page abonnementPage = abonnementService.abonnements(page,10);
        model.addAttribute("abonnements", abonnementPage);
        model.addAttribute("abonnement", new Abonnement());
        return "abonnements/list";
    }

    @GetMapping("/souscription")
    public String souscription(Model model, @RequestParam(defaultValue = "0") int page){
        Page abonnementPage = abonnementService.abonnements(page,10);
        abonnementPage.forEach(abonnement->{

        });
        model.addAttribute("abonnements", abonnementPage);
        return "abonnements/souscription";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("abonnement") Abonnement abonnement){
        abonnementService.add(abonnement);
        return "redirect:/abonnement/list";
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
