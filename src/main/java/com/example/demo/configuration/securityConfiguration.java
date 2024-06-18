package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class securityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(registry ->{
            registry.requestMatchers("/home").permitAll();
            registry.requestMatchers("/users").hasRole("USER");
            registry.requestMatchers("/admin**").hasRole("ADMIN");
            registry.anyRequest().authenticated();
        })
                .formLogin(formLogin ->formLogin.permitAll())
        .build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("$2a$12$gWvF.2cYbBYkysaXThdrXOR73MX1wAWGW8cwhVP88Hw9aeNTbcVZ6")
                .roles("ADMIN","USER")
                .build();
        UserDetails users = User.builder()
                .username("dpan")
                .password("$2a$12$yi9D5D46VCWEz8pYOr4vp.8uPNVAgES3/KF0k2iLIgT9KeTcm1.RG")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
