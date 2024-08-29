package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;

	@NotNull(message = "customer_id cannot be null")
	@Positive(message = "customer_id must be a positive number")
	@Column(name = "customer_id")
	private int customerId;

	@NotNull(message = "room_id cannot be null")
	@Positive(message = "room_id must be a positive number")
	@Column(name = "room_id")
	private int roomId;

	@NotEmpty(message = "Status cannot be empty")
	@Column(name = "booking_status")
	private String bookingStatus;

	public Booking() {
	}

	public Booking(int customerId, int roomId, String bookingStatus) {
		super();
		this.customerId = customerId;
		this.roomId = roomId;
		this.bookingStatus = bookingStatus;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

}
