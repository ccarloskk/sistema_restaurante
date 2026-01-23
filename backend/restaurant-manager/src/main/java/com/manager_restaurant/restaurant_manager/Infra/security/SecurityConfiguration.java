package com.manager_restaurant.restaurant_manager.Infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/auth/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/me").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/auth/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/auth/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/products/admin/menu").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/menu").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/{idProduct}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/products/{idProduct}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/products/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/orders/details").permitAll()
                        .requestMatchers(HttpMethod.POST, "/orders/updateTotalPublic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/orders/createOrder").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/orders/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/order_item/details").permitAll()
                        .requestMatchers(HttpMethod.POST, "/order_item/CreateOrderItem").permitAll()
                        .requestMatchers(HttpMethod.DELETE ,"/order_item/**").permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "http://localhost:8080",
                "http://127.0.0.1:5500"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

