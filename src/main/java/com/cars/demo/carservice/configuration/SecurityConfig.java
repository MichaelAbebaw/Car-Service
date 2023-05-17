package com.cars.demo.carservice.configuration;

import com.cars.demo.carservice.entity.UserAccount;
import com.cars.demo.carservice.repository.UserManagementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
/*
    @Bean
    public UserDetailsService userDetailsService() {
        // hard coded users
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build()
        );
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build()
        );
        userDetailsManager.loadUserByUsername("admin").getPassword();
        return userDetailsManager;
    }
*/
    @Bean
    public CommandLineRunner initUsers (UserManagementRepository userManagementRepository) {
        // start up load
        return arg -> {
            userManagementRepository.save(new UserAccount("user1", "password", "USER"));
            userManagementRepository.save(new UserAccount("user2", "password", "ADMIN"));
            userManagementRepository.save(new UserAccount("user3", "password", "USER", "ADMIN"));
        };
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService (UserManagementRepository userManagementRepository) {
        return username -> userManagementRepository.findByUsername (username).asUser();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.formLogin();
        httpSecurity.httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public SecurityFilterChain configureSecurity (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/api/v1/cars")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/cars/add")
            .hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/v1/cars/delete")
            .hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/v1/cars/update")
            .hasRole("USER")
            .anyRequest().denyAll()
            .and().formLogin()
            .and().httpBasic()
                .and().csrf().disable().build();
    }
}
