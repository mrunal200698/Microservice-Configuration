package com.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.entities.Rating;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	

	@PostMapping
	public ResponseEntity<Rating> createRatings(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRatings(rating));
	}
	
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		return ResponseEntity.ok(ratingService.getAllRating());
	}

	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsUsingUserid(@PathVariable String userId){
		return ResponseEntity.ok(ratingService.getRatingByUserid(userId));
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingUsingHotelid(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getRatingByHotelid(hotelId));
	}
}
