package com.po.mathrock.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        auth.userDetailsService(userDetailsService);
    }



    @Override
    protected void configure(HttpSecurity httpSec) throws Exception {
        httpSec
            .csrf().disable()
            // check the following requests and authorized according to the rules (antmacher() + condition)
            .authorizeRequests()
                .antMatchers("/", "/index").authenticated()
                .antMatchers("/registration").permitAll()
            // IMPORTANT!!! all the requests require authentication
            .anyRequest().authenticated()
            .and()
                // without this the user can not be authenticated
                // here define which endpoint should be used in case of login is necessary
                .formLogin().loginPage("/login").permitAll()
            .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll()
                ;
    }
}
