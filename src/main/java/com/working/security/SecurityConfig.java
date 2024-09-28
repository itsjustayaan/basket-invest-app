package com.working.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.working.authentication.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF for development, ensure it's enabled in production
            .authorizeHttpRequests((requests) -> requests
            		.requestMatchers("/**").permitAll()
//                .requestMatchers("/login", "/accessDenied").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/ia/**").hasRole("INVESTMENT_ADVISOR")
//                .requestMatchers("/investor/**").hasRole("INVESTOR")
//                .requestMatchers("/ia/**").permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin((form) -> form
//                .loginPage("/login") // Custom login page
//                .defaultSuccessUrl("/home") // Redirect after successful login
//                .permitAll()
//            )
//            .logout((logout) -> logout
//                .logoutSuccessUrl("/login?logout") // Redirect after logout
//                .permitAll()
//            )
//            .exceptionHandling((exceptionHandling) -> exceptionHandling
//                .accessDeniedPage("/accessDenied")
            );

        return http.build();
    }
}
