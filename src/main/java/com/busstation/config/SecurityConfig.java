package com.busstation.config;

import com.busstation.config.jwt.JwtAuthTokenFilter;
import com.busstation.utils.JwtProviderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    UserDetailsService userService;

    @Autowired
    JwtProviderUtils jwtProvider;

    @Autowired
    LogoutHandler logoutHandler;

    @Bean
    public JwtAuthTokenFilter jwtAuthTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/users/**").permitAll()
                .requestMatchers("/api/v1/roles/**").permitAll()
                .requestMatchers("/api/v1/employees/**").permitAll()
                .requestMatchers("/api/v1/tickets/**").permitAll()
                .requestMatchers("/seat-booking").permitAll()
                .requestMatchers("/seat-booking/**").permitAll()
                .requestMatchers("/api/v1/cars/**").permitAll()
                .requestMatchers("/api/v1/trips/**").permitAll()
                .requestMatchers("/api/v1/chairs/**").permitAll()
                .requestMatchers("/api/v1/orders/**").permitAll()
                .anyRequest()
                .authenticated().and().authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class).logout()
                .logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()).and()
                .httpBasic(withDefaults()).sessionManagement().sessionCreationPolicy(STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
