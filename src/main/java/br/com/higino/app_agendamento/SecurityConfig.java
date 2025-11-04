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
            // páginas públicas e assets
            .requestMatchers(
              "/app_agendamento/", 
              "/app_agendamento/index.html",
              "/app_agendamento/loginAcesso.html",
              "/app_agendamento/loginDesenvolvedor.html",
              "/app_agendamento/cadastroLojas.html",
              "/app_agendamento/**.css", "/app_agendamento/**.js",
              "/app_agendamento/**.png", "/app_agendamento/**.jpg", "/app_agendamento/**.jpeg",
              "/app_agendamento/Estados.json", "/app_agendamento/Cidades.json"
            ).permitAll()

            // APIs públicas de login e criação de loja
            .requestMatchers(
              "/app_agendamento/api/lojas/login",
              "/app_agendamento/api/dev-login",
              "/app_agendamento/api/lojas"          // POST de cadastro
            ).permitAll()

            // painel do DEV só com ROLE_DEV
            .requestMatchers(
              "/app_agendamento/painelDesenvolvedor.html",
              "/app_agendamento/api/**/dev/**",
              "/app_agendamento/api/lojas/**"      // ajuste se sua API de lojas é só dev
            ).hasRole("DEV")
            .requestMatchers("/api/lojas/**").hasRole("DEV")

            // painel de cliente logado
            .requestMatchers("/app_agendamento/painel/**").authenticated()

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
