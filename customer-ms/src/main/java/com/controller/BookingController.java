package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.CustomException.InvalidBookingDetailsException;
import com.exceptions.CustomException.RoomAlreadyBookedException;
import com.model.Booking;
import com.service.BookingService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/customers")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	@PostMapping("/bookings")
	public ResponseEntity<?> bookRoom(@Valid @RequestBody Booking booking) {
		try {
			Booking savedBooking = bookingService.bookRoom(booking);
			logger.info("Booking successful: {}", savedBooking);
			return ResponseEntity.ok(savedBooking);
		} catch (RoomAlreadyBookedException e) {
			logger.warn("Room already booked: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (InvalidBookingDetailsException e) {
			logger.error("Invalid booking details: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{customer_id}/bookings")
	public ResponseEntity<List<Booking>> getBookingsByCustomerId(
			@Positive @PathVariable("customer_id") int customerId) {
		List<Booking> bookings = bookingService.getBookingsByCustomerId(customerId);
		logger.info("Retrieved {} bookings for customer ID: {}", bookings.size(), customerId);
		return ResponseEntity.ok(bookings);
	}
}
