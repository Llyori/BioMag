package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.Utility;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.services.ClientService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class AuthController {
    private ClientService clientService;
    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/login")
    public String login(){
        return "authentification/login";
    }
    @GetMapping("/")
    public String index() { return "home-pages/index"; }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("client", new Client());
        return "authentification/register";
    }
    @PostMapping("/register")
    public String saveClient(@ModelAttribute("client") Client client, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Endpoint ajout, "+client);
        if(client.getPassword().equals(client.getConfPassword()) && clientService.findByEmail(client.getEmail()) == null && clientService.findByPhoneNumber(client.getPhoneNumber()) == null && client.getNumIdCart().toString().length() == 17) {
            clientService.add(client);
            String siteURL = Utility.getSiteURL(request);
            clientService.sendVerificationEmail(client, siteURL);
            return "redirect:/login";
        }else if(clientService.findByEmail(client.getEmail()) != null){
            return "redirect:/register?errem=true";
        }else if(!(client.getPassword().equals(client.getConfPassword())))
            return "redirect:/register?error=true";
        else if(clientService.findByPhoneNumber(client.getPhoneNumber()) != null)
            return "redirect:/register?errph=true";
        else if(client.getNumIdCart().toString().length() != 17)
            return "redirect:/register?erric=true";
        else
            return "redirect:/register";
    }
    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model model){
        boolean verified = clientService.verify(code);
        String pageTitle = verified ? "Verification Success" : "Verfication Failed";
        model.addAttribute("pageTitle", pageTitle);
        return "authentification/" + (verified ? "verifySuccess" : "verifyFail");
    }
}
