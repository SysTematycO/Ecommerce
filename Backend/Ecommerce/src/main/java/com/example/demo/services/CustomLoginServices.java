package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class CustomLoginServices implements AuthenticationProvider {
  

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
    public CustomLoginServices() {}
    

	@Override
    public Authentication authenticate(Authentication authentication){
    	String email = authentication.getName();
    	String chain = authentication.getCredentials().toString();
    	UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(email);
    	
    	if(userDetails != null && chain.equals(userDetails.getPassword())) {
    		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    	}
    	
        return null;
    }
    
	@Override
	public boolean supports(Class<?> authentication) {
	    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}