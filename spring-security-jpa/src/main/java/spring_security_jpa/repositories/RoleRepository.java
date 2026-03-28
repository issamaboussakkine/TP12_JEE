package spring_security_jpa.repositories;

// Issam : Imports
import spring_security_jpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Issam : Repository pour l'entité Role
 * Hérite des méthodes CRUD de JpaRepository
 * Auteur : Issam ABOUSSAKKINE
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Issam : Recherche un rôle par son nom
     * @param name Le nom du rôle (ex: "ADMIN", "USER")
     * @return Le rôle trouvé ou null
     */
    Role findByName(String name);
}