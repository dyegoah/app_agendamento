package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 🔓 ROTAS PÚBLICAS
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/loginAcesso.html",
                    "/loginDesenvolvedor.html", 
                    "/agendamentoServico.html", // ✅ PÚBLICO
                    "/agendamento-publico",     // ✅ PÚBLICO
                    "/css/**",
                    "/js/**", 
                    "/images/**",
                    "/favicon.ico",
                    "/static/**"
                ).permitAll()
                // 🔓 APIs PÚBLICAS
                .requestMatchers(
                    "/api/agendamento-servico/**", // ✅ API PÚBLICA
                    "/api/lojas/**",
                    "/api/public/**"
                ).permitAll()
                // 🔒 ROTAS PROTEGIDAS (requerem autenticação)
                .requestMatchers(
                    "/painel/**",
                    "/cadastroLojas.html", 
                    "/painelGerencia.html",
                    "/agendamentoDespesa.html"
                ).authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/loginAcesso.html") // Página de login customizada
                .loginProcessingUrl("/api/auth/login") // URL de processamento do login
                .defaultSuccessUrl("/painel/painelGerencia.html", true) // Redirecionamento após login
                .failureUrl("/loginAcesso.html?error=true") // Em caso de falha
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/loginAcesso.html?logout=true")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/loginAcesso.html") // Redireciona para login se não autenticado
            );

        return http.build();
    }
}