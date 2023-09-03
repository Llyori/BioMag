package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.RoleClientForm;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping(path = "/list")
    public ResponseEntity<Object> clients(){
        return new ResponseEntity<>(clientService.list(), HttpStatus.ACCEPTED);
    }
    @PostMapping(path = "/new")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        return new ResponseEntity<>(clientService.add(client), HttpStatus.ACCEPTED);
    }
    @PostMapping(path = "/addRoleToClient")
    public void addRoleToClient(@RequestBody RoleClientForm roleClientForm){
        clientService.addRoletoUser(roleClientForm.getEmail(), roleClientForm.getRoleName());
    }
}