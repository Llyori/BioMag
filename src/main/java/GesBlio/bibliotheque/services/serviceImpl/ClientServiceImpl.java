package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.entities.AppRoles;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.repositories.AppRolesRepository;
import GesBlio.bibliotheque.repositories.ClientRepository;
import GesBlio.bibliotheque.services.ClientService;
import com.sun.mail.util.MailConnectException;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.List;
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private AppRolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender mailSender;

    public ClientServiceImpl(ClientRepository clientRepository, AppRolesRepository rolesRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.clientRepository = clientRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @Override
    public Client add(Client client) {
        String pw = client.getPassword();
        client.setPassword(passwordEncoder.encode(pw));
        String randomCode = RandomString.make(64);
        client.setCodeVerification(randomCode);
        return clientRepository.save(client);
    }
    @Override
    public boolean sendVerificationEmail(Client client, String siteURL){
        try{
            String subject = "Please verify your registration";
            String senderName = "BioMag";
            String mailContent = "<p>Dear " + client.getFirstName() + ",</p>";
            mailContent += "<p> Please click the link below to verify to your registration:<p>";
            String verifyURL = siteURL + "/verify?code=" + client.getCodeVerification();
            mailContent += "<a href=\"" + verifyURL + "\"> VERIFY </a>";
            mailContent += "<p>Thank you<br>The BioMag Team</p>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("gesmed27@gmail.com", senderName);
            helper.setTo(client.getEmail());
            helper.setSubject(subject);
            helper.setText(mailContent, true);
            mailSender.send(message);
            return true;
        }
        catch(MailConnectException m ){
            return false;
        } catch(MessagingException m ){
            return false;
        } catch(UnsupportedEncodingException m ){
            return false;
        }
    }

    @Override
    public boolean sendPasswordRecuverEmail(Client client, String siteURL) {
        try{
            String subject = "Password Recuver";
            String senderName = "BioMag";
            String mailContent = "<p>Dear " + client.getFirstName() + ",</p>";
            mailContent += "<p> you are about to reset your password please follow the link above:<p>";
            String verifyURL = siteURL + "/recuver?code=" + client.getCodeVerification();
            mailContent += "<a href=\"" + verifyURL + "\"> Reset Now </a>";
            mailContent += "<p>Thank you<br>The BioMag Team</p>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("gesmed27@gmail.com", senderName);
            helper.setTo(client.getEmail());
            helper.setSubject(subject);
            helper.setText(mailContent, true);
            mailSender.send(message);
            return true;
        } catch(MailConnectException m ){
            return false;
        } catch(MessagingException m ){
            return false;
        } catch(UnsupportedEncodingException m ){
            return false;
        }

    }

    @Override
    public boolean verify(String codeverification) {
        Client client = clientRepository.findByCodeVerification(codeverification);
        if(client == null || client.isEnabled())
            return false;
        else{
            clientRepository.enable(client.getIdClient());
            return true;
        }
    }

    @Override
    public boolean confirm(String codeverification) {
        Client client = clientRepository.findByCodeVerification(codeverification);
        if(client == null)
            return false;
        else{
            return true;
        }
    }

    @Override
    public Client Update(Client client) {
        Client client1 = clientRepository.findById(client.getIdClient()).get();
        client1 = client;
        return clientRepository.save(client1);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client findByPhoneNumber(String phone) {
        return clientRepository.findByPhoneNumber(phone);
    }

    @Override
    public Client findByCodeVerification(String codeVerification) {
        return clientRepository.findByCodeVerification(codeVerification);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void addRoletoUser(String email, String roleName) {
        Client client = clientRepository.findByEmail(email);
        AppRoles appRoles = rolesRepository.findByRoleName(roleName);
        client.getAppRoles().add(appRoles);
        clientRepository.save(client);
    }
}
