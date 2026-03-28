package spring_security_jpa.config;

// Issam : Imports
import spring_security_jpa.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Issam : Configuration de la sécurité Spring
 * Auteur : Issam ABOUSSAKKINE
 */
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Issam : Constructeur avec injection du service utilisateur
     * @param userDetailsService Service pour charger les utilisateurs
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Issam : Bean pour encoder les mots de passe avec BCrypt
     * @return BCryptPasswordEncoder pour sécuriser les mots de passe
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Issam : Bean pour l'authentification avec DAO
     * Lie le UserDetailsService et le PasswordEncoder
     * @return DaoAuthenticationProvider configuré
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Issam : Filtre principal de sécurité
     * Définit les règles d'autorisation et la configuration du formulaire
     * @param http Configuration de sécurité
     * @return SecurityFilterChain configuré
     * @throws Exception En cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Issam : Configuration des autorisations par URL
                .authorizeHttpRequests(auth -> auth
                        // Issam : Pages publiques (accessibles sans authentification)
                        .requestMatchers("/login", "/register").permitAll()
                        // Issam : Pages ADMIN - réservées aux administrateurs
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Issam : Pages USER - accessibles aux utilisateurs et administrateurs
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        // Issam : Toute autre page nécessite une authentification
                        .anyRequest().authenticated()
                )

                // Issam : Configuration du formulaire de connexion personnalisé
                .formLogin(form -> form
                        .loginPage("/login")                 // Issam : Page de login personnalisée
                        .defaultSuccessUrl("/home", true)    // Issam : Redirection après succès
                        .failureUrl("/login?error=true")     // Issam : Redirection après échec
                        .permitAll()                         // Issam : Rendre la page accessible
                )

                // Issam : Configuration de la déconnexion
                .logout(logout -> logout
                        .logoutUrl("/logout")                // Issam : URL de déconnexion
                        .logoutSuccessUrl("/login?logout=true") // Issam : Redirection après déconnexion
                        .permitAll()                         // Issam : Accessible à tous
                );

        return http.build();
    }
}