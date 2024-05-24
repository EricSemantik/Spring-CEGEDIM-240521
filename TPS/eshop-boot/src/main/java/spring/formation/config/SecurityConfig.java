package spring.formation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import spring.formation.config.jwt.JwtHeaderAuthorizationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderAuthorizationFilter jwtFilter) throws Exception {
		// Méthode d'authentification par HTTP Basic
		http.httpBasic(Customizer.withDefaults());

		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/api/utilisateur/connexion").permitAll();
			authorize.requestMatchers("/api/utilisateur/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			authorize.requestMatchers("/api/**").authenticated();
			authorize.requestMatchers("/**").permitAll();
		});

		// Désactiver la protection CSRF
		http.csrf(c -> c.ignoringRequestMatchers("/api/**"));

		// Configurer les CORS (Cross-Origine Resources Sharing)
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();

				// On autorise tout le monde
				config.setAllowedOrigins(List.of("*"));

				// On autorise toutes les commandes HTTP (GET, POST, PUT, ...)
				config.setAllowedMethods(List.of("*"));

				// On autorise toutes les en-têtes HTTP
				config.setAllowedHeaders(List.of("*"));

				return config;
			};

			c.configurationSource(source);
		});

		// Positionner le filtre JWT AVANT le filter
		// UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	// Grace à ce Bean, on pourra injecter un AuthenticationManager directement
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Pas d'encadage sur les mots de passe - PAS BIEN
//		return NoOpPasswordEncoder.getInstance();

		// Encodage Blowfish
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public UserDetailsService inMemory() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("client").password("{noop}123456").roles("CLIENT").build());
//		manager.createUser(User.withUsername("fournisseur").password("{noop}123456").roles("FOURNISSEUR").build());
//		manager.createUser(User.withUsername("admin").password("{noop}123456").roles("ADMIN").build());
//		manager.createUser(User.withUsername("sa").password("{noop}123456").roles("SUPER_ADMIN").build());
//		return manager;
//	}
}
