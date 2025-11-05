package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // usando fetch/SPA
            .headers(h -> h
                .frameOptions(f -> f.deny())
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; script-src 'self' https://cdn.jsdelivr.net https://code.jquery.com; style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; img-src 'self' data:;")
                )
            )
            .authorizeHttpRequests(auth -> auth
                // páginas públicas e assets (sem prefixo app_agendamento)
                .requestMatchers(
                    "/", 
                    "/index.html",
                    "/loginAcesso.html",
                    "/loginDesenvolvedor.html",
                    "/cadastroLojas.html",
                    "/**.css", "/**.js",
                    "/**.png", "/**.jpg", "/**.jpeg",
                    "/Estados.json", "/Cidades.json"
                ).permitAll()

                // APIs públicas de login e criação de loja
                .requestMatchers(
                    "/api/lojas/login",
                    "/api/dev-login",
                    "/api/lojas" // POST de cadastro
                ).permitAll()

                // painel do DEV só com ROLE_DEV
                .requestMatchers(
                    "/painelDesenvolvedor.html",
                    "/api/**/dev/**",
                    "/api/lojas/**" // ajuste se sua API de lojas é só dev
                ).hasRole("DEV")

                // painel de cliente logado
                .requestMatchers("/painel/**").authenticated()

                // o resto autenticado
                .anyRequest().authenticated()
            )
            // sem formulário padrão
            .formLogin(f -> f.disable())
            // basic é útil para testes, mas não será usado pelo front
            .httpBasic(h -> {});

        return http.build();
    }
}
