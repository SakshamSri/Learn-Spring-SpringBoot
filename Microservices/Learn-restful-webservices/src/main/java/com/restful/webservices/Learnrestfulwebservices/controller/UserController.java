package com.restful.webservices.Learnrestfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.restful.webservices.Learnrestfulwebservices.bean.Post;
import com.restful.webservices.Learnrestfulwebservices.bean.User;
import com.restful.webservices.Learnrestfulwebservices.exception.PostDetailsNotValidException;
import com.restful.webservices.Learnrestfulwebservices.exception.PostNotFoundException;
import com.restful.webservices.Learnrestfulwebservices.exception.UserDetailsNotValidException;
import com.restful.webservices.Learnrestfulwebservices.exception.UserNotFoundException;
import com.restful.webservices.Learnrestfulwebservices.repository.PostRepository;
import com.restful.webservices.Learnrestfulwebservices.repository.UserRepository;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("Id=" + id + " does not exist.");

		EntityModel<User> userModel = EntityModel.of(user.get()); // implementing hateoas, adding model of type user

		WebMvcLinkBuilder linkRetrieveUsersBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers());
		userModel.add(linkRetrieveUsersBuilder.withRel("all-users")); // for all getUser, we will return a link to
																		// getAllUsers link

		return userModel;
	}

	@PostMapping("/users") // @RequestBody makes so that any input coming from the post request will be
							// mapped to the user object
							// @Valid makes so that the incoming data verifies all fields in User class that
							// have some validations
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		if (user.getName() == null || user.getBirthDate() == null)
			throw new UserDetailsNotValidException("User details are not valid. Name=" + user.getName() + " BirthDate="
					+ user.getBirthDate().toString());
		User save = userRepository.save(user);

		// get the location of new user created. It will be created at /users/{id} where
		// id will be save.getId()
		URI saveUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId())
				.toUri();

		return ResponseEntity.created(saveUserUri).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("Id=" + id + " does not exist.");

		userRepository.delete(user.get());

		return ResponseEntity.noContent().build(); // This will return a 204 No Content response, we can change the
													// return to void for 200 OK response as well
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> getAllPostsOfOneUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById((long) id);
		if (!user.isPresent())
			throw new UserNotFoundException("Id=" + id + " does not exist.");

		List<Post> posts = user.get().getPosts();

		if (posts.size() == 0)
			throw new PostNotFoundException("No Posts exists of user with Id=" + id + ".");

		return posts;
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> AddPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById((long) id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("Id=" + id + " does not exist.");

		User user = userOptional.get();

		if (post.getDescription() == null)
			throw new PostDetailsNotValidException("Post details are not valid. Description=null");

		post.setUser(user);
		postRepository.save(post);

		URI saveUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(saveUserUri).build();
	}
}
