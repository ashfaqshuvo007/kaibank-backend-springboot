package com.kaibank.system.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The WebSecurityConfig class is used to define web security configurations for KiiBank system.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final KaiBankAuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    public WebSecurityConfig(ObjectMapper objectMapper) {
        this.authenticationEntryPoint = new KaiBankAuthenticationEntryPoint(objectMapper);
        this.accessDeniedHandler = new AccessDeniedHandler(objectMapper);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.httpBasic()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/auth/login")
        .permitAll()
        .antMatchers("/branches")
        .permitAll()
        .antMatchers("/roles")
        .permitAll()
        .antMatchers(HttpMethod.GET,"/customers/myData")
        .hasAnyAuthority("ROLE_CUSTOMER")
        .antMatchers(HttpMethod.POST,"/transactions/transfer-in-mobile")
        .hasAnyAuthority("ROLE_CUSTOMER")
        .antMatchers("/employees/**")
        .hasAnyAuthority("ROLE_ADMIN")
        .antMatchers("/transactionTypes", "/transactions/**")
        .hasAnyAuthority("ROLE_MANAGER")
        .antMatchers("/customers/**")
        .hasAnyAuthority("ROLE_MANAGER")
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint);
    http.cors();
  }
}
