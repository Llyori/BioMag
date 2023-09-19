package GesBlio.bibliotheque.repositories;

import GesBlio.bibliotheque.entities.AppRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRolesRepository extends JpaRepository<AppRoles, Long> {
    AppRoles findByRoleName(String roleName);
}
