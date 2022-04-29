package com.enterarte.config;


import com.enterarte.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
     @Autowired
    public CustomerService customerService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customerService).
                passwordEncoder(new BCryptPasswordEncoder());   
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
                 http .csrf().disable()
                .authorizeRequests() 
                .antMatchers("/index", "/css/**", "/js/**","/main/**", "/img/**", "/video/**", "/login/**","/admin/**", "/customer/**").permitAll()           
                .antMatchers(HttpMethod.GET).permitAll()      
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("clave")
                .defaultSuccessUrl("/main")
                .failureUrl("/login/login?error=true").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll();
                
    }
    


}
