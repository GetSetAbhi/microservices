package com.example.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.example.restfulwebservices.db.PostRepository;
import com.example.restfulwebservices.db.UserRepository;
import com.example.restfulwebservices.exceptions.UserNotFoundException;
import com.example.restfulwebservices.model.Post;
import com.example.restfulwebservices.model.User;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> opUser = userRepository.findById(id);
		
		if (!opUser.isPresent()) {
			String message = "User with id %s does not exit";
			throw new UserNotFoundException(String.format(message, id));
		}
		
		User user = opUser.get();
		
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

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {		
		userRepository.deleteById(id);
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI
	
	//HATEOAS
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		//User savedUser = service.save(user);
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		Optional<User> opUser = userRepository.findById(id);
		
		if (!opUser.isPresent()) {
			String message = "User with id %s does not exit";
			throw new UserNotFoundException(String.format(message, id));
		}
		
		User user = opUser.get();
		
		
		return user.getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/post")
	public ResponseEntity<Object> createUserPosts(@PathVariable int id, @RequestBody Post post) {
		Optional<User> opUser = userRepository.findById(id);
		
		if (!opUser.isPresent()) {
			String message = "User with id %s does not exit";
			throw new UserNotFoundException(String.format(message, id));
		}
		
		User user = opUser.get();
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();
			
		return ResponseEntity.created(location).build();
	}
}
