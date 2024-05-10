package com.user.entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;


@Entity
@Table(name="microuser")
@Builder
public class User {

	@Id
	@Column(name="Id")
	private String userId;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="City")
	private String city;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId){
		this.userId=userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public User(String userId, String name, String email, String city,List<Rating> ratings) {
		this.userId=userId;
		this.name=name;
		this.email=email;
		this.city=city;
		this.ratings=ratings;
	}
	
	public User() {
		
	}
}
