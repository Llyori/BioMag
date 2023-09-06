package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.RoleClientForm;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @PostMapping(path = "/new")
//    public ResponseEntity<Client> saveClient(@RequestBody Client client){
//        System.out.println("Endpoint ajout, "+client);
//        if(client.getPassword().equals(client.getConfPassword())) {
//            System.out.println("Iciiiiiiiiii");
//            return new ResponseEntity<>(clientService.add(client), HttpStatus.ACCEPTED);
//        }else
//            return null;
//    }
    @PostMapping(path = "/addRoleToClient")
    public void addRoleToClient(@RequestBody RoleClientForm roleClientForm){
        clientService.addRoletoUser(roleClientForm.getEmail(), roleClientForm.getRoleName());
    }
}