package spring_security_jpa;

// Issam : Imports Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Issam : Application principale Spring Security avec JPA
 * Auteur : Issam ABOUSSAKKINE
 */
@SpringBootApplication
public class SpringSecurityJpaApplication {

	public static void main(String[] args) {
		// Issam : Lancement de l'application
		SpringApplication.run(SpringSecurityJpaApplication.class, args);

		// Issam : Message de confirmation
		System.out.println("=====================================");
		System.out.println("Spring Security JPA - Issam");
		System.out.println("Application démarrée avec succès !");
		System.out.println("=====================================");
	}
}