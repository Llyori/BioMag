package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.Client;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ClientService {
    public List<Client> list();
    public Client add(Client client);
    public Client Update(Client client);
    public Client findById(Long id);
    public Client findByEmail(String email);
    public Client findByPhoneNumber(String phone);
    public void delete(Long id);
    void addRoletoUser(String username, String roleName);
    void sendVerificationEmail(Client client, String siteURL) throws MessagingException, UnsupportedEncodingException;
    boolean verify(String codeverification);
}
