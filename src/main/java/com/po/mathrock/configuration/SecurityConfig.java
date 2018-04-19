package com.po.mathrock.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSec) throws Exception {
        httpSec
            // check the following requests and authorized according to the rules (antmacher() + condition)
            .authorizeRequests()
                .antMatchers("/", "/index")
                    .hasRole("USER")
            // IMPORTANT!!! all the requests require authentication
            .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                    .permitAll()
            .and()
                .logout().logoutSuccessUrl("/login?logout")
                    .permitAll()
                ;
    }
}