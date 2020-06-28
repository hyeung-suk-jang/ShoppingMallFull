package com.example.parayo.config;


import com.example.parayo.config.filters.JwtAuthenticationFilter;
import com.example.parayo.config.security.JwtAccessDeniedHandler;
import com.example.parayo.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Filter filter = new JwtAuthenticationFilter(
                authenticationManager(), jwtUtil());

        http
                .cors()
                    .disable()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .headers().frameOptions().disable()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                    .addFilter(filter)
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/api/hcheck").permitAll()
                    .antMatchers("/api/v1/users").permitAll()
                    .antMatchers("/api/v1/signin").permitAll()
                .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }
}
