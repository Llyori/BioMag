package GesBlio.bibliotheque.web;

import GesBlio.bibliotheque.Utility;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.entities.ProfilUtilisateur;
import GesBlio.bibliotheque.services.CategorieService;
import GesBlio.bibliotheque.services.ClientService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.Principal;

@Controller
public class AuthController {
    private static Client cl = new Client();
    private ClientService clientService;
    private CategorieService categorieService;
    public AuthController(ClientService clientService, CategorieService categorieService) {
        this.clientService = clientService;
        this.categorieService = categorieService;
    }
    @GetMapping("/login")
    public String login(){
        return "authentification/login";
    }
    @GetMapping("/")
    public String index() { return "home-pages/index"; }
    @GetMapping("/about")
    public String aboutPage() { return "home-pages/about"; }
    @GetMapping("/contact")
    public String contactPage() { return "home-pages/contact"; }
    @GetMapping("/index")
    public String home(Model model) {
        Client client = clientService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("client", client);
        if(client.isFirstLogin){
            model.addAttribute("categories", categorieService.categories());
            model.addAttribute("profil", new ProfilUtilisateur());
            return "clients/profilUtilisateur";
        }
        else{
            return "index";
        }
    }
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("profil") ProfilUtilisateur profilUtilisateur){
        System.out.println(profilUtilisateur);
        return "index";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("client", new Client());
        return "authentification/register";
    }
    @GetMapping("/nouveau")
    public String nouveau(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add";
    }
    @GetMapping("/profil")
    public String profil(Model model, @RequestParam("idUtilisateur") Long idUtilisateur) {
        model.addAttribute("client", clientService.findById(idUtilisateur));
        return "clients/profil";
    }
    @PostMapping("/register")
    public String saveClient(@ModelAttribute("client") Client client, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException, UnknownHostException {
        cl = client;
        if(client.getPassword().equals(client.getConfPassword()) && clientService.findByEmail(client.getEmail()) == null && clientService.findByPhoneNumber(client.getPhoneNumber()) == null && client.getNumIdCart().toString().length() == 17) {
            clientService.add(client);
            String siteURL = Utility.getSiteURL(request);
            boolean b = clientService.sendVerificationEmail(client, siteURL);
            if(!b)
                return "errors/errorSmtp";
            else
                return "authentification/verify";
        }else if(clientService.findByEmail(client.getEmail()) != null && clientService.findByEmail(client.getEmail()).isEnabled()){
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
        if(verified)
            clientService.newProfile(new ProfilUtilisateur(clientService.findByEmail(cl.getEmail())));
        String pageTitle = verified ? "Verification Success" : "Verfication Failed";
        model.addAttribute("pageTitle", pageTitle);
        return "authentification/" + (verified ? "verifySuccess" : "verifyFail");
    }
    @GetMapping("/resendCode")
    public String resendCodeEmail(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String siteURL = Utility.getSiteURL(request);
        clientService.sendVerificationEmail(cl, siteURL);
        return "authentification/verify";
    }

    //recuperation du mot de passe de l'utilisateur
    //formulaire de recuperation
    @GetMapping("/passwordRecuver")
    public String recuverPassword(HttpServletRequest request){
        return "authentification/passwordRecuver";
    }

    @PostMapping("/passwordRecuver")
    public String sendRecuverMail(@RequestParam("email") String email, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        cl = clientService.findByEmail(email);
        if(cl != null && cl.isEnabled()){
            String siteURL = Utility.getSiteURL(request);
            clientService.sendPasswordRecuverEmail(cl, siteURL);
            return "authentification/recuver";
        }
        return "redirect:/passwordRecover?error=true";
    }

    //
    @GetMapping("/recuver")
    public String recuverPassword(@Param("code") String code, Model model, RedirectAttributes ra){
        boolean verified = clientService.confirm(code);
        //recuperation si code correct
        if(verified){
           model.addAttribute("token", code);
           return "authentification/formRecuverPassword";
        }
        ra.addFlashAttribute("error", "désolé une erreur s'est produite");
        return "redirect:/login";
    }

    @PostMapping("/recuver")
    public String changePassword(@RequestParam("token") String code, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, RedirectAttributes ra){
        boolean verified = clientService.confirm(code);
        //recuperation si code correct
        if(verified){
            if(!(password.equals(confirmPassword))){
                return "redirect:/recuver?error=true";
            }
            cl = clientService.findByCodeVerification(code);
            Client client = clientService.findById(cl.getIdClient());
            client.setPassword(password);
            client.setConfPassword(confirmPassword);
            clientService.add(client);
            ra.addFlashAttribute("success", "mot de passe modifié avec succès");
            return "redirect:/login";
        }

        ra.addFlashAttribute("error", "désolé une erreur s'est produite");
        return "redirect:/login";
    }

    @GetMapping("/resendRecuverCode")
    public String resendRecuverEmailCode(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String siteURL = Utility.getSiteURL(request);
        clientService.sendPasswordRecuverEmail(cl, siteURL);
        return "authentification/recuver";
    }
}
