package bac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

/**
 * @author Alexei
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
 //   AuthenticationService authenticationService;

//    @Autowired
//    AuthenticationEntryPoint _authPoint;

//     @Autowired
//    AuthenticationTokenProcessingFilter _authProcessingFilter;

    private static AuthenticationEntryPoint authenticationEntryPoint;
//    private static AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

    @PostConstruct
    private void init() {
//        authenticationEntryPoint = _authPoint;
//        authenticationTokenProcessingFilter = _authProcessingFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/", "/**").permitAll()
                // added "/**" just to get swagger running: TODO: Check how to do it without permitting /**
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
//                .addFilterBefore(authenticationTokenProcessingFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth

 //               .userDetailsService(authenticationService)
  //              .passwordEncoder(passwordEncoder());
    }

}
