package com.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entities.Rating;
import com.user.entities.User;
import com.user.entities.Hotel;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.service.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userrepository;


	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User createUser(User user) {
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return  userrepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
	}

	@Override
	//ya mdhe single user sbt rating pn ali pahije mnun rating service sbt communicate kraila rest template waprla ahe
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		//get user from database with the help of user repository
		User user = userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with this ID: "+userId+" does not exist!"));
		
		//fetch rating of the above user from RATING SERVICE
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingsOfUser);
		
		List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			//api call to hotel service to get the hotel
			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = forEntity.getBody();
			logger.info("response status code: {} ",forEntity.getStatusCode());
			//set the hotel for rating
			rating.setHotel(hotel);
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}

	
	
}
