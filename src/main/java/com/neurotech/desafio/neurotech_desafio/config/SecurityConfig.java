package com.neurotech.desafio.neurotech_desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.neurotech.desafio.neurotech_desafio.auth.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Bean
  UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails user = User.withUsername("admin")
        .password(encoder.encode("admin123"))
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
    return cfg.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(Customizer.withDefaults())
      .sessionManagement(sm -> sm.sessionCreationPolicy(
          org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth -> auth
          .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
          .requestMatchers(
              "/swagger-ui/**",
              "/swagger-ui.html",
              "/v3/api-docs/**",
              "/actuator/health"
          ).permitAll()
          .requestMatchers("/auth/login").permitAll()
          .requestMatchers("/produtos/**").authenticated()
          .anyRequest().permitAll()
      )
      .httpBasic(b -> b.disable())
      .formLogin(f -> f.disable());

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
