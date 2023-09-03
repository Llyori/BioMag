package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.entities.AppRoles;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.repositories.AppRolesRepository;
import GesBlio.bibliotheque.repositories.ClientRepository;
import GesBlio.bibliotheque.services.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private AppRolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository, AppRolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @Override
    public Client add(Client client) {
        String pw = client.getPassword();
        client.setPassword(passwordEncoder.encode(pw));
        return clientRepository.save(client);
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
