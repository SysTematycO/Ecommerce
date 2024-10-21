package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.models.AuthenticationResponse;
import com.example.demo.security.JwtUtil;

@Service
public class LoginServices {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public LoginServices() {}
	
	public AuthenticationResponse login(String email, String chain) {
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(email, chain);
        try {
            Authentication authenticate = this.authenticationManager.authenticate(authenticationRequest);
            
            if(authenticate.isAuthenticated()) {
            	String token = this.jwtUtil.generateToken(email);
            	
                List<String> roles = new ArrayList<>();
                authenticate.getAuthorities().forEach(authority -> {
                    roles.add(authority.getAuthority()); 
                });
            	
             authenticationResponse = this.generateResponseOk(email, token, roles);
            	 
            } else {
            	authenticationResponse = this.generateResponseDenied(email);

            }
        } catch (Exception e) {
        	authenticationResponse = this.generateResponseDenied(email);

        }

        return authenticationResponse;
	}
	
    public AuthenticationResponse generateResponseOk(String email, String token, List<String> role) {
    	AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    	authenticationResponse.setEmail(email);
    	authenticationResponse.setToken(token);
    	authenticationResponse.setRole(role);
    	authenticationResponse.setStatus("OK");
    	
    	return authenticationResponse;
    }
    
    public AuthenticationResponse generateResponseDenied(String email) {
    	AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    	authenticationResponse.setEmail(email);
    	authenticationResponse.setStatus("Creedenciales Invalidas");
    	
    	return authenticationResponse;
    }

}
