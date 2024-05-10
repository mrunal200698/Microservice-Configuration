package com.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hotel.entities.Hotel;
import com.hotel.repositories.HotelRepository;
import com.hotel.service.HotelService;
import com.hotel.service.exception.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService {

	private HotelRepository hotelrepository;
	
	public HotelServiceImpl(HotelRepository hotelrepository) {
		this.hotelrepository = hotelrepository;
	}

	@Override
	public Hotel createHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		String ramdomHotelId=UUID.randomUUID().toString();
		hotel.setHotelId(ramdomHotelId);
		return hotelrepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hotelrepository.findAll();
	}

	@Override
	public Hotel getSingleHotel(String hotelId) {
		// TODO Auto-generated method stub
		return hotelrepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with this ID: "+hotelId+" does not exist."));
	}

}
