package spring_security_jpa.config;

import spring_security_jpa.entities.Role;
import spring_security_jpa.entities.User;
import spring_security_jpa.repositories.RoleRepository;
import spring_security_jpa.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {

            // Issam : Vérifier si des rôles existent déjà
            if (roleRepo.count() == 0) {

                // Issam : Création des rôles
                Role adminRole = new Role("ROLE_ADMIN");
                Role userRole = new Role("ROLE_USER");

                roleRepo.save(adminRole);
                roleRepo.save(userRole);

                // Issam : Récupérer les rôles sauvegardés
                Role savedAdminRole = roleRepo.findByName("ROLE_ADMIN");
                Role savedUserRole = roleRepo.findByName("ROLE_USER");

                // Issam : Création des utilisateurs
                User admin = new User(
                        "admin",
                        encoder.encode("1234"),
                        true,
                        List.of(savedAdminRole, savedUserRole)
                );

                User user = new User(
                        "user",
                        encoder.encode("1111"),
                        true,
                        List.of(savedUserRole)
                );

                userRepo.save(admin);
                userRepo.save(user);

                System.out.println("=====================================");
                System.out.println("Issam : Base de données initialisée !");
                System.out.println("Comptes créés :");
                System.out.println("  - admin / 1234 (ADMIN, USER)");
                System.out.println("  - user / 1111 (USER)");
                System.out.println("=====================================");
            }
        };
    }
}