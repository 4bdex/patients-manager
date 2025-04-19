package ma.enset.patients_manager.security.repository;

import ma.enset.patients_manager.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}