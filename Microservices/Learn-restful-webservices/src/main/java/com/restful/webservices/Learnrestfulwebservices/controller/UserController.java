package com.restful.webservices.Learnrestfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.webservices.Learnrestfulwebservices.bean.User;
import com.restful.webservices.Learnrestfulwebservices.exception.UserDetailsNotValidException;
import com.restful.webservices.Learnrestfulwebservices.exception.UserNotFoundException;
import com.restful.webservices.Learnrestfulwebservices.repository.UserDaoService;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userDaoService.findUser(id);
		if (user == null)
			throw new UserNotFoundException("Id=" + id + " does not exist.");
		return user;
	}

	@PostMapping("/users") // @RequestBody makes so that any input coming from the post request will be
							// mapped to the user object
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		if (user.getName() == null || user.getBirthDate() == null)
			throw new UserDetailsNotValidException("User details are not valid. Name=" + user.getName() + " BirthDate="
					+ user.getBirthDate().toString());
		User save = userDaoService.save(user);

		// get the location of new user created. It will be created at /users/{id} where
		// id will be save.getId()
		URI saveUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId())
				.toUri();

		return ResponseEntity.created(saveUserUri).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		User user = getUser(id);
		userDaoService.deleteUser(user);

		return ResponseEntity.noContent().build(); // This will return a 204 No Content response, we can change the
													// return to void for 200 OK response as well
	}
}
