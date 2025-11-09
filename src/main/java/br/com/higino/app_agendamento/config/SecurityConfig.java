package br.com.higino.app_agendamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.higino.app_agendamento.repository.LojaRepository;
import br.com.higino.app_agendamento.security.LojaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(LojaRepository lojaRepository, PasswordEncoder encoder) {
        return new LojaUserDetailsService(lojaRepository, encoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                // Público
                .requestMatchers(
                    "/", "/loginAcesso.html", "/cadastroLojas.html",
                    "/publico/**",
                    "/api/lojas", "/api/lojas/existe",
                    "/api/publico/**",
                    "/css/**", "/js/**", "/images/**", "/fonts/**", "/favicon.ico"
                ).permitAll()
                // Privado
                .requestMatchers("/painel/**").authenticated()
                // Dev
                .requestMatchers("/dev_login.html", "/dev_painel.html", "/api/dev/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/loginAcesso.html")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("senha")
                .defaultSuccessUrl("/painel/painelGerencia.html", true)
                .failureUrl("/loginAcesso.html?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginAcesso.html?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}
