package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 🔓 Páginas e APIs públicas
                .requestMatchers(
                    "/painel/agendamentoPublico.html",
                    "/api/agendamento-servico/**",
                    "/css/**", "/js/**", "/img/**", "/uploads/**"
                ).permitAll()

                // 🔒 Protege o resto
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/painel/painelGerencia.html", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
            // 🚫 Desabilita CSRF apenas para a API pública
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/agendamento-servico/**"));

        return http.build();
    }
}
