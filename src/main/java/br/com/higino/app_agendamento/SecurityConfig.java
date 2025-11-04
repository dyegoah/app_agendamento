package br.com.higino.app_agendamento;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 🔹 Libera toda a API e os arquivos estáticos
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/app_agendamento/api/**",
                    "/app_agendamento/**",
                    "/painel/**",
                    "/css/**",
                    "/js/**",
                    "/imagens/**",
                    "/static/**",
                    "/**"
                ).permitAll()
                .anyRequest().permitAll()
            )

            // 🔹 Desativa completamente CSRF (para projeto com páginas estáticas)
            .csrf(csrf -> csrf.disable())

            // 🔹 Habilita CORS
            .cors(cors -> cors.configurationSource(request -> {
                var config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("*"));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(false);
                return config;
            }));

        return http.build();
    }
    
   
  

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
