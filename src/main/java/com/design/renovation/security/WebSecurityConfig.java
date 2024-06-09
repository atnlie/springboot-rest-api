package com.design.renovation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // pay attention on this
        .cors(cors -> cors.disable())
// allow all
//        .authorizeHttpRequests(
//            (a) -> a.anyRequest().permitAll()
//        );
//  restrict all
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated()

        )
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(
        "/api/v1/users/register",
        "/api/v1/users/login"

    );
  }


}
