package ru.otus.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((r) -> r.requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/book/**", "/author/**", "/genre/**", "api/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "addBook").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "addGenre").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "addAuthor").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "addcomment").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                        .anyRequest().denyAll()
                ).formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
