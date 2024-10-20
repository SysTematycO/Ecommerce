package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Users;
import com.example.demo.models.User;
import com.example.demo.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServices userService;
	
	@GetMapping("/getAllUsers")
    public ResponseEntity<ArrayNode> getAllUsers(@RequestHeader("Authorization") String token){

        ObjectMapper objectMapper = new ObjectMapper();

        List<Users> users = this.userService.getAllUsers(); 
        
        ArrayNode usersArray = objectMapper.valueToTree(users);
        
        return ResponseEntity.ok(usersArray);
    }
	
	@PostMapping("/addUser")
    public ResponseEntity<ObjectNode> addUser(@RequestHeader("Authorization") String token, @RequestBody User user){

		ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseJson = objectMapper.createObjectNode();

		boolean addUser = this.userService.addUser(user);
		
		if(addUser) {
			 responseJson.put("status", "success");
             responseJson.put("message", "Usuario agregado exitosamente");
             return ResponseEntity.ok(responseJson);
		} else {
            responseJson.put("status", "error");
            responseJson.put("message", "El usuario ya existe");
            return ResponseEntity.ok(responseJson);
		}
		
    }
	
	@PutMapping("/updateUser")
    public ResponseEntity<ObjectNode> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseJson = objectMapper.createObjectNode();

        boolean updateUser = this.userService.updateUser(user);

        if (updateUser) {
            responseJson.put("status", "success");
            responseJson.put("message", "Usuario actualizado exitosamente");
            return ResponseEntity.ok(responseJson);
        } else {
            responseJson.put("status", "error");
            responseJson.put("message", "No se pudo actualizar el usuario o no existe");
            return ResponseEntity.ok(responseJson);
        }
    }
	

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<ObjectNode> deleteUser(@RequestHeader("Authorization") String token, @PathVariable String email) {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseJson = objectMapper.createObjectNode();

        boolean deleteUser = this.userService.deleteUser(email);

        if (deleteUser) {
            responseJson.put("status", "success");
            responseJson.put("message", "Usuario eliminado exitosamente");
            return ResponseEntity.ok(responseJson);
        } else {
            responseJson.put("status", "error");
            responseJson.put("message", "No se pudo eliminar el usuario o no existe");
            return ResponseEntity.ok(responseJson);
        }
    }
}
