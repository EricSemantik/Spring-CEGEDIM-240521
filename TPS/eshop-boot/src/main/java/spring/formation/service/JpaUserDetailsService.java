package spring.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUtilisateurRepository utilisateurRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = this.utilisateurRepo.findByIdentifiant(username)
				.orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas."));

		// Si l'utilisateur n'a pas été trouvé, l'exception sera jetée, et on s'arrêtera
		// là

		User.UserBuilder userBuilder = User.withUsername(username).password(passwordEncoder.encode(utilisateur.getMotDePasse()))
				.disabled(!utilisateur.isActive());

		List<String> roles = utilisateur.getRoles().stream().map(Role::name).toList();

		userBuilder.roles(roles.toArray(new String[0]));

		return userBuilder.build();
	}

}
