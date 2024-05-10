package com.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {
	
	//custom finder methods for find bye userid and find by hotelid
	
	List<Rating> findByuserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
	
}
