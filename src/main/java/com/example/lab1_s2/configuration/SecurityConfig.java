package com.example.lab1_s2.configuration;

import com.example.lab1_s2.service.UserDetails; // your custom UserDetailsService class
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetails userDetailsService;

    public SecurityConfig(UserDetails customUserDetails) {
        this.userDetailsService = customUserDetails;
    }

    // Step 5–10: Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 6) Disable CSRF (development)
        http.csrf(csrf -> csrf.disable());

        // 7) URL Authorization rules
        http.authorizeHttpRequests(auth -> auth
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                .requestMatchers("/register", "/login", "/error").permitAll()
                .anyRequest().authenticated()
        );

        // 8) Custom login page + behaviours
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/welcome", true)
                .permitAll()
        );

        // 9) Logout behaviour
        http.logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );

        // 10) Use your custom UserDetailsService
        http.userDetailsService(userDetailsService);

        return http.build();
    }

    // 11) Password Encoder Bean (fixes your PasswordEncoder autowire problem)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
