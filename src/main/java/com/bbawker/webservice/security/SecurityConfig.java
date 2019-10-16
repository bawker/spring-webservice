package com.bbawker.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.authorizeRequests()
//
            .antMatchers("/easyAdmin/createAccount", "/easyAdmin/login", "/h2-console/**", "/api/**").permitAll()
//            .antMatchers("/easyAdmin/**").hasRole("ADMIN")
            .antMatchers("/easyAdmin/**").authenticated()
            .and()
                .formLogin()
                .loginPage("/easyAdmin/login")
                .usernameParameter("email")
                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/easyAdmin/test")
                .successHandler(successHandler())
                .failureUrl("/easyAdmin/login")
            .and()
                .logout()
                .logoutSuccessUrl("/easyAdmin/login")
            .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**", "/api/**")
            .and()
                .headers()
                .addHeaderWriter(
                    new XFrameOptionsHeaderWriter(
                            new WhiteListedAllowFromStrategy(Arrays.asList("localhost"))
                    )
            );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");//default로 이동할 url
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
