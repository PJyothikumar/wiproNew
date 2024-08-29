package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;
	
	@NotEmpty(message = "name is mandatory")
	private String name;
	
	@NotEmpty(message = "location is mandatory")
	private String location;
	
	@NotEmpty(message = "facilities is mandatory")
	private String facilities;
	
	@NotNull(message = "price cannot be null")
	private Double price;
	
	@NotEmpty(message = "status is mandatory")
	private String status;

	public Room() {

	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Room(Integer roomId, String name, String location, String facilities, Double price, String status) {
		this.roomId = roomId;
		this.name = name;
		this.location = location;
		this.facilities = facilities;
		this.price = price;
		this.status = status;
	}
}
