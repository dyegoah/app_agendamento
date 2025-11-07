package br.com.higino.app_agendamento;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				// Libera recursos estáticos (CSS, JS, imagens, favicon etc.)
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

				// ✅ ADICIONE ESTA LINHA ABAIXO:
				.requestMatchers("/painel/**").permitAll().requestMatchers("/api/dev-login").permitAll()
				.requestMatchers("/api/dev-session").permitAll()

				.requestMatchers("/", "/index.html", "/loginAcesso.html", "/loginDesenvolvedor.html",
						"/cadastroLojas.html", "/painelGerencia.html", "/agendamentoServico.html",
						"/agendamentoDespesa.html", "/css/**", "/js/**", "/images/**", "/favicon.ico", "/static/**",
						"/publico/**" // ✅ nova linha
				).permitAll()
				// APIs públicas
				.requestMatchers("/api/lojas/**", "/api/public/**", "/api/agendamento-servico/**").permitAll()
				// Demais rotas
				.requestMatchers("/dev/login", "/dev/painel").permitAll().anyRequest().permitAll())
				.formLogin(f -> f.disable()).httpBasic(h -> h.disable());

		return http.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns("*") // ✅ CORRETO no Spring 6
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowCredentials(true);
			}
		};
	}
}
