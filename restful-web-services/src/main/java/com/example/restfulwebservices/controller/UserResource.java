package com.example.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restfulwebservices.dao.services.UserDaoService;
import com.example.restfulwebservices.exceptions.UserNotFoundException;
import com.example.restfulwebservices.model.User;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user == null) {
			String message = "User with id %s does not exit";
			throw new UserNotFoundException(String.format(message, id));
		}
		
		return user;
	}
	
	@GetMapping("/hateoas/users/{id}")
	public EntityModel<User> retrieveUserHateoas(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user == null) {
			String message = "User with id %s does not exit";
			throw new UserNotFoundException(String.format(message, id));
		}
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()
		);
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	
	/**
	 * Go to postman, enter url http://localhost:8080/users
	 * then switch to body tab and from dropdown at the right select json
	 * also select ray and in the text area below enter a user object values in json format
	 * {
	 * 	 "name": "R"
	 * }
	 * to either fr, nl or en
	 * 
	 * **/
	
	//
	// input - details of user
	// output - CREATED & Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
			
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		User user = service.deleteUserById(id);
		
		if (user == null) {
			String message = "User with id %s does not exit";
			throw new UserNotFoundException(String.format(message, id));
		}
		
		
		return ResponseEntity.ok().build();
	}
	
}