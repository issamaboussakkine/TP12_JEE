package spring_security_jpa.controller;

// Issam : Imports Spring MVC
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Issam : Contrôleur pour la gestion des pages d'authentification
 * Auteur : Issam ABOUSSAKKINE
 */
@Controller
public class AuthController {

    /**
     * Issam : Affiche la page de connexion
     * URL : /login
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Issam : Affiche la page d'accueil après connexion
     * URL : /home
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * Issam : Affiche le tableau de bord administrateur
     * URL : /admin/dashboard
     * Accès réservé aux utilisateurs avec le rôle ADMIN
     */
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    /**
     * Issam : Affiche le tableau de bord utilisateur
     * URL : /user/dashboard
     * Accès réservé aux utilisateurs avec le rôle USER ou ADMIN
     */
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }
}