package com.user.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entities.User;
import com.user.entities.User.UserBuilder;
import com.user.service.UserService;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	 
	//create user
	@PostMapping
	public 	ResponseEntity<User> createUser(@RequestBody User user){
		User user1= userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	//get all users
	@GetMapping
	public 	ResponseEntity<List<User>> getAllUsers(){
		List<User> allusers= userService.getAllUser();
		return ResponseEntity.ok(allusers); 
	}
	/*
	int retryCount=1;
	//get one single user   //using Retry
	@GetMapping("/{userId}")
	@Retry(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
	public 	ResponseEntity<User> getSingleUser(@PathVariable String userId){
		retryCount++;
		User user= userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	*/
	
	
	
	//get one single user   using circuitbreaker
	@GetMapping("/{userId}")
	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
	public 	ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user= userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	/*
	//get one single user   //using ratelimiter
		@GetMapping("/{userId}")
		@RateLimiter(name="userRateLimiter",fallbackMethod="ratingHotelFallback")
		public 	ResponseEntity<User> getSingleUser(@PathVariable String userId){
			User user= userService.getUser(userId);
			return ResponseEntity.ok(user);
		}
	*/
	//creating fallback method for circuit breaker and fallback is executed because service is down
	public 	ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		ex.printStackTrace();
		User user = User.builder()
			.email("dummy@gmail.com")
			.name("Dummy")
			.city("This user is the citizen of dummy city.")
			.userId("141234")
			.build();
		return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
	}
	
	
}
