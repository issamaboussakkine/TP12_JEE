package spring_security_jpa.repositories;  // Issam : Package sans tiret

// Issam : Imports
import spring_security_jpa.entities.User;  // Issam : Import unique et corrigé
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Issam : Repository pour l'entité User
 * Hérite des méthodes CRUD de JpaRepository
 * Auteur : Issam ABOUSSAKKINE
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Issam : Recherche un utilisateur par son nom d'utilisateur
     * @param username Le nom d'utilisateur
     * @return Optional contenant l'utilisateur s'il existe
     */
    Optional<User> findByUsername(String username);
}