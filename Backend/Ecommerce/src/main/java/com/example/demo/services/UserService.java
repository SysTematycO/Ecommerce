package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.models.User;
import com.example.demo.repositories.RiUsers;

@Service
public class UserService {
	
	@Autowired
    private RiUsers riUsers;
	
	public UserService() {}
	
	public List<Users> getAllUsers() {
        return this.riUsers.findAll();
	}
	
	public boolean addUser(User user) {
		
		if (this.riUsers.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }
		
        Users newUser = new Users();
        newUser.setEmail(user.getEmail());
        newUser.setChain(user.getChain());
        newUser.setRole(user.getRole()); 

        try {
           this.riUsers.save(newUser);
           return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	public boolean updateUser(User user) {
		
        return this.riUsers.findByEmail(user.getEmail()).map(existingUser -> {
            existingUser.setChain(user.getChain());
            existingUser.setRole(user.getRole());

            try {
                this.riUsers.save(existingUser);
                return true;
            } catch (Exception e) {
                return false;
            }
        }).orElse(false);
    }
    
    public boolean deleteUser(String email) {
    	
        return this.riUsers.findByEmail(email).map(user -> {
            try {
                this.riUsers.delete(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }).orElse(false); 
    }
}
