package com.api.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.model.User;

@RestController
public class UserController {
	
	@Value("${db : default value}")
	private String url;
	
	@Value("${credential}")
	private String[] dbCredential;
	
	@Value("#{${mapvalues}}")
	private Map<String, Integer> mapValues;
	
	List<User> usersList = new ArrayList<User>();
	
	@GetMapping("/api/active-profile")
	public String getActiveProfile() {
		return url;
	}
	
	@GetMapping("/api/getCredential")
	public String[] getCredential() {
		return dbCredential;
	}
	
	@GetMapping("/api/getMapValues")
	public Map<String, Integer> getMapValues() {
		return mapValues;
	}
	
	@PostMapping(value="/api/save-user")
	public String addUser(@RequestBody User user) {
		usersList.add(user);
		return "Successfully added";
	}
	
	@GetMapping("/api/get-users")
	public List<User> getAllUsers() {
		return usersList;
	}
	
	@PutMapping("/api/update/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User user) {
		for (User currUser : usersList) {
			if(currUser.getId()==id) {
				currUser.setName(user.getName());
				currUser.setAge(user.getAge());
			}
		}
		return "Successfully Updated";
	}
	
	@DeleteMapping("/api/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		User targetUser = new User();
		for (User user : usersList) {
			if(user.getId()==id) {
				targetUser=user;
			}
		}
		usersList.remove(targetUser);
		return "Successfully deleted";
	}

}
