package GesBlio.bibliotheque.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalExceptionHandler implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Récupérer les informations sur l'erreur à partir de la requête
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = "Une erreur inattendue s'est produite.";

        // Utiliser le code d'erreur pour personnaliser le message d'erreur affiché à l'utilisateur
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                message = "La ressource demandée est introuvable.";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value() || statusCode == HttpStatus.METHOD_NOT_ALLOWED.value())
                message = "Une erreur interne du serveur s'est produite.";
            else if(RequestDispatcher.ERROR_MESSAGE.equals("java.net.UnknownHostException")){
                message = "Impossible d'atteindre l'hote, veuillez ressayer plus tard.";
            }
        }

        // Passer le message d'erreur à la vue Thymeleaf
        model.addAttribute("errorMessage", message);

        // Renvoyer la vue d'erreur personnalisée
        return "errors/errorSmpt";
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
