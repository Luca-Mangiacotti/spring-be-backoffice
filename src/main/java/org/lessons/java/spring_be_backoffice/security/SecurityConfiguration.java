package org.lessons.java.spring_be_backoffice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .requestMatchers("/comics/create", "comics/edit/**").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/comics/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/categories").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/categories/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/comics", "/comics/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/**").permitAll()
                .requestMatchers("/api/comics/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authproProvider = new DaoAuthenticationProvider();

        // qui si utilizzeranno i servizi di recupero username degli user e utilizzo del
        // passwordencoder
        authproProvider.setUserDetailsService(userDetailService());
        authproProvider.setPasswordEncoder(passwordEncoder());

        return authproProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET");
            }
        };
    }
}
