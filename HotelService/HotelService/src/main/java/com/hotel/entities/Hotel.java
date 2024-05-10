package com.hotel.entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="microhotel")
public class Hotel {
	@Id
	@Column(name = "Id")
	private String hotelId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "About")
	private String about;
	
	@Transient
	private List<Rating> ratings= new ArrayList<>();
	
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Hotel(String hotelId, String name, String location, String about,List<Rating> ratings) {
		this.hotelId = hotelId;
		this.name = name;
		this.location = location;
		this.about = about;
		this.ratings=ratings;
	}
	
	public Hotel() {
		
	}
}
