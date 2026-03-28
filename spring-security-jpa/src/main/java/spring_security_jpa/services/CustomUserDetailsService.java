package spring_security_jpa.services;

import spring_security_jpa.entities.User;
import spring_security_jpa.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        // Issam : Récupération des autorités (rôles)
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Issam : Vérifier que les rôles ne sont pas null
        if (user.getRoles() != null) {
            for (Object roleObj : user.getRoles()) {
                // Issam : Cast en Role
                if (roleObj instanceof spring_security_jpa.entities.Role) {
                    spring_security_jpa.entities.Role role = (spring_security_jpa.entities.Role) roleObj;
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true, true, true,
                authorities
        );
    }
}