package com.dto;

public class RoomDTO {
	private Integer roomId;
	private String name;
	private String location;
	private String facilities;
	private Double price;
	private String status;

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

	public RoomDTO(Integer roomId, String name, String location, String facilities, Double price, String status) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.location = location;
		this.facilities = facilities;
		this.price = price;
		this.status = status;
	}

	public RoomDTO() {
		super();
	}

}
