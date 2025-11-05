package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔹 Controle de permissões por rota
            .authorizeHttpRequests(auth -> auth

                // === 🟢 ROTAS PÚBLICAS ===
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/uploads/**",
                    "/painel/agendamentoServico.html",    // página pública de agendamento
                    "/painel/painelDesenvolvedor.html",    // página pública de desenvolvedor
                    "/api/agendamento-servico/**"          // APIs acessadas pela página pública
                ).permitAll()

                // === 🔒 TODAS AS OUTRAS PÁGINAS EXIGEM LOGIN ===
                .anyRequest().authenticated()
            )

            // 🔹 Configuração da página de login
            .formLogin(login -> login
                .loginPage("/login")                // sua página de login personalizada
                .defaultSuccessUrl("/painel", true) // redireciona após login bem-sucedido
                .permitAll()
            )

            // 🔹 Permite logout normalmente
            .logout(LogoutConfigurer::permitAll)

            // 🔹 Desabilita CSRF para evitar bloqueio em fetch() públicos
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
