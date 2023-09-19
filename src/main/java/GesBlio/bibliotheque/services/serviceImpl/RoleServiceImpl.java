package GesBlio.bibliotheque.services.serviceImpl;

import GesBlio.bibliotheque.entities.AppRoles;
import GesBlio.bibliotheque.repositories.AppRolesRepository;
import GesBlio.bibliotheque.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    private AppRolesRepository appRolesRepository;
    public RoleServiceImpl(AppRolesRepository appRolesRepository) {
        this.appRolesRepository = appRolesRepository;
    }
    @Override
    public List<AppRoles> list() {
        return appRolesRepository.findAll();
    }
    @Override
    public AppRoles add(AppRoles appRoles) {
        return appRolesRepository.save(appRoles);
    }
    @Override
    public AppRoles Update(AppRoles appRoles) {
        AppRoles appRoles1 = appRolesRepository.findById(appRoles.getIdRole()).get();
        appRoles1 = appRoles;
        return appRolesRepository.save(appRoles1);
    }
    @Override
    public AppRoles findById(Long id) {
        return appRolesRepository.findById(id).get();
    }
    @Override
    public AppRoles findByRoleName(String roleName) {
        return appRolesRepository.findByRoleName(roleName);
    }
    @Override
    public void delete(Long id) {
        appRolesRepository.deleteById(id);
    }
}
