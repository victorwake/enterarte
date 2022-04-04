package com.enterarte.config;


import com.enterarte.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
//        http.headers().frameOptions().sameOrigin().and()
                 http
                .authorizeRequests()
                .antMatchers("/index", "/css/**", "/js/**","/main/**", "/img/**", "/video/**", "/login/**","/admin/**", "/customer/**").permitAll()
                .antMatchers("/author/create").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("clave")
                .defaultSuccessUrl("/main/main")
                .failureUrl("/login/login?error=true").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/login").permitAll()
                .and().csrf().disable();
                
    }
    

}
