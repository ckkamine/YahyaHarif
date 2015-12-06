package com.lambda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambda.entities.User;
import com.lambda.security.SaltedSHA256PasswordEncoder;
import com.lambda.security.TokenUtils;






@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Configuration
    public static class ApiWebSecurityConfigurerAdapter
            extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private SaltedSHA256PasswordEncoder saltedPasswordEncoder;


        @Override
        protected void configure(HttpSecurity http) throws Exception {

        	http.csrf().disable();
    		http.httpBasic().disable();
    		http.headers().contentTypeOptions().disable();
    		http.headers().httpStrictTransportSecurity().disable();
    		http.headers().xssProtection().disable();
    		http.headers().cacheControl().disable();


    		

    		http.authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll();
    		http.authorizeRequests().antMatchers("/user/**").permitAll();
    		http.authorizeRequests().antMatchers("/rest/admin/**").hasRole(User.ROLE_ADMIN);
    		http.authorizeRequests().antMatchers("/rest/encadrant/**").hasRole(User.ROLE_EVALUATEUR);
    		http.authorizeRequests().antMatchers("/rest/collaborateur/**").hasRole(User.ROLE_COLLABORATEUR);
    		http.authorizeRequests().antMatchers("/rest/manager/**").hasRole(User.ROLE_MANAGER);
    		
        
      
    		//Token Configuration
    		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    		SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter = new XAuthTokenConfigurer(userDetailsService);
            http.apply(securityConfigurerAdapter);
        }
        @Override
    	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
    			throws Exception {
    		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(saltedPasswordEncoder);
    			
    	}

    	@Bean
    	@Override
    	public AuthenticationManager authenticationManagerBean() throws Exception {
    		return super.authenticationManagerBean();
    	}

    }
	
}
