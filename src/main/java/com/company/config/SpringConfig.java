package com.company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Authentication
        auth.inMemoryAuthentication()
                .withUser("admin").password("{bcrypt}$2a$10$cfIe02ix3qm5fohDvR/APeTrpyA50nYaS/fsm8hQLqU1BPQ15/w3W").roles("admin")
                .and()
                .withUser("profile").password("{bcrypt}profilejon").roles("profile");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization
        http.authorizeRequests()
                .antMatchers("/category/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("admin")
                .antMatchers("/profile/**").hasAnyRole("profile")
                .anyRequest().authenticated()
                .and().httpBasic();
        http.csrf().disable().cors().disable();

//                .and().formLogin();
    }
}
