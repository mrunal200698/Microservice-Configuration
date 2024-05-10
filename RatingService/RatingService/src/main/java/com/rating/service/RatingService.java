package com.rating.service;

import java.util.List;

import com.rating.entities.Rating;

public interface RatingService {
		
		//create ratings
		Rating createRatings(Rating rating);
		
		//get all ratings
		List<Rating> getAllRating();
		
		//get rating with userid
		List<Rating> getRatingByUserid(String userId);
		
		//get rating with hotelid
		List<Rating> getRatingByHotelid(String hotelId);
		
}
