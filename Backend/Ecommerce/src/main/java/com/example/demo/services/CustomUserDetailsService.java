package com.example.demo.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.RiUsers;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private RiUsers riUsers;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> users = this.riUsers.findByEmail(username);
		
		if(users.isPresent()) {
			return new User(users.get().getEmail(), users.get().getChain(), this.getAuthorities(users));
		}
		
		return null;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Optional<Users> users) {
	    return Arrays.asList(new SimpleGrantedAuthority(users.get().getRole()));
	}
}
