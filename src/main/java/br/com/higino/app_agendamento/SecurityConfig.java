package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Libera recursos estáticos (CSS, JS, imagens, favicon etc.)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(
                    "/", 
                    "/index.html",
                    "/loginAcesso.html",
                    "/loginDesenvolvedor.html",
                    "/cadastroLojas.html",
                    "/painelGerencia.html",
                    "/agendamento-publico",
                    "/api/agendamentoServico.html",
                    "/agendamentoDespesa.html",
                    // Se seus estáticos estão fora de common locations,
                    // libere explicitamente com /** (e não com /**/**)
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/favicon.ico",
                    "/static/**"
                ).permitAll()
                // APIs públicas
                .requestMatchers("/api/lojas/**", "/api/public/**").permitAll()
                // Demais rotas
                .anyRequest().permitAll()
            )
            .formLogin(f -> f.disable())
            .httpBasic(h -> h.disable());

        return http.build();
    }
}
