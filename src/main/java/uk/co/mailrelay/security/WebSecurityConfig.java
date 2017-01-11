package uk.co.mailrelay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
        	.and()
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/api/**").authenticated();
    }
}
