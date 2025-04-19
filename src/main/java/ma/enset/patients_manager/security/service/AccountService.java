package ma.enset.patients_manager.security.service;

import ma.enset.patients_manager.security.entities.AppRole;
import ma.enset.patients_manager.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String Password, String email,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
}