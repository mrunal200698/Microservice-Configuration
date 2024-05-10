package com.hotel.service;

import java.util.List;

import com.hotel.entities.Hotel;

public interface HotelService {
	//create
	Hotel createHotel(Hotel hotel);
	
	//get all hotels
	List<Hotel> getAllHotels();
	
	//get single hotel
	Hotel getSingleHotel(String hotelId);
	
}
