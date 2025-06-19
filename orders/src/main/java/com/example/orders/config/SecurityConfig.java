package com.example.orders.config;

import com.common.entities.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(aurhz -> aurhz
                            .requestMatchers(HttpMethod.GET, "/api/orders/**").authenticated()
                                    .requestMatchers(HttpMethod.POST,"/api/orders/**").authenticated()
                                    .requestMatchers(HttpMethod.PUT,"/api/orders/**").authenticated()
                                    .requestMatchers(HttpMethod.DELETE,"/api/orders/**").authenticated()
                                    .anyRequest().authenticated()
                            )
                    .httpBasic(Customizer.withDefaults());
            return http.build();
        }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        CustomUserDetails user = new CustomUserDetails(
                42L,
                "usuarioPrueba",
                passwordEncoder.encode("1234"),
                AuthorityUtils.createAuthorityList("ROLE_USER")
        );

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
