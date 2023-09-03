package GesBlio.bibliotheque.services;

import GesBlio.bibliotheque.entities.AppRoles;

import java.util.List;

public interface RoleService {
    public List<AppRoles> list();
    public AppRoles add(AppRoles appRoles);
    public AppRoles Update(AppRoles appRoles);
    public AppRoles findById(Long id);
    public AppRoles findByRoleName(String roleName);
    public void delete(Long id);
}
