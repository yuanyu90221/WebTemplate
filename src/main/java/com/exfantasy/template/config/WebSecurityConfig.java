package com.exfantasy.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.exfantasy.template.security.authentication.MyAuthenticationFailureHandler;
import com.exfantasy.template.security.authentication.MyAuthenticationProvider;
import com.exfantasy.template.security.service.MyUserDetailsService;

/**
 * Ref. https://spring.io/guides/gs/securing-web/
 * 
 * @author tommy.feng
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
            	.antMatchers("/", "/home", "/css/**").permitAll()
            	.antMatchers("/user/register").permitAll() // for api do register
            	.anyRequest().authenticated() // 除了上面兩組, 輸入任何 request 都會先被導到 login
            	.and()
            .formLogin()
            	.loginPage("/login").permitAll()
            	.failureHandler(myAuthenticationFailureHandler)
            	.and()
            .logout().permitAll()
            	.and()
            .csrf().disable();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
        auth.authenticationProvider(myAuthenticationProvider);
    }
}
