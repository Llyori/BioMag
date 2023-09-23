package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.RoleClientForm;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping(path = "/list")
    public ResponseEntity<Object> clients(){
        return new ResponseEntity<>(clientService.list(), HttpStatus.ACCEPTED);
    }
    @PostMapping(path = "/addRoleToClient")
    public void addRoleToClient(@RequestBody RoleClientForm roleClientForm){
        clientService.addRoletoUser(roleClientForm.getEmail(), roleClientForm.getRoleName());
    }
    @GetMapping("/find/id/{idClient}")
    public ResponseEntity<Client> findById(@Param("idClient") Long idClient){
        return new ResponseEntity<>(clientService.findById(idClient), HttpStatus.OK);
    }
    @GetMapping("/find/name/{firstname}")
    public ResponseEntity<Client> findByName(@Param("firstname") String firstname){
        return new ResponseEntity<>(clientService.findByFirstName(firstname), HttpStatus.OK);
    }
    @GetMapping("/find/phone/{phoneNumber}")
    public ResponseEntity<Client> findByNumber(@Param("phoneNumber") String phoneNumber){
        return new ResponseEntity<>(clientService.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }
    @GetMapping("/find/email/{email}")
    public ResponseEntity<Client> findByEmail(@Param("email") String email){
        return new ResponseEntity<>(clientService.findByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Client> update(@RequestBody Client client){
        return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.ACCEPTED);
    }
    @GetMapping("/delete/{idClient}")
    public void delete(@Param("idClient") Long idClient){
        clientService.delete(idClient);
    }
}