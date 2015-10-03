package com.schultzco.webservices.config;

import org.pac4j.core.client.Clients;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.springframework.security.authentication.ClientAuthenticationProvider;
import org.pac4j.springframework.security.authentication.ClientAuthenticationToken;
import org.pac4j.springframework.security.web.ClientAuthenticationEntryPoint;
import org.pac4j.springframework.security.web.ClientAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Clients clients;

    @Autowired
    private FacebookClient facebookClient;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final ClientAuthenticationFilter clientFilter = new ClientAuthenticationFilter("/callback");
        clientFilter.setClients(clients);
        clientFilter.setAuthenticationManager(authenticationManager());
        final ClientAuthenticationEntryPoint facebookEntryPoint = new ClientAuthenticationEntryPoint();
        facebookEntryPoint.setClient(facebookClient);
        http
                .authorizeRequests().antMatchers("/div/**").hasRole("USER").and()
                .addFilterBefore(clientFilter, AnonymousAuthenticationFilter.class).exceptionHandling()
                .authenticationEntryPoint(facebookEntryPoint)
            .and()
                .csrf().disable();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        final ClientAuthenticationProvider clientProvider = new ClientAuthenticationProvider();
        clientProvider.setClients(clients);
        clientProvider.setUserDetailsService(authenticationUserDetailsService());
        auth.authenticationProvider(clientProvider);
    }

    @Bean
    public AuthenticationUserDetailsService<ClientAuthenticationToken> authenticationUserDetailsService() throws Exception {
        return customUserDetailsService;
    }
}
