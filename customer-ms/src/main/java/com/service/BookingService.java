package com.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.exceptions.CustomException.BookingNotFoundException;
import com.exceptions.CustomException.InvalidBookingDetailsException;
import com.exceptions.CustomException.RoomAlreadyBookedException;
import com.model.Booking;
import com.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

	public Booking bookRoom(Booking booking) {
		try {
			boolean roomAlreadyBooked = bookingRepository.existsByRoomIdAndBookingStatus(booking.getRoomId(), "BOOKED");

			if (roomAlreadyBooked) {
				logger.warn("Room with ID {} is already booked.", booking.getRoomId());
				throw new RoomAlreadyBookedException("The room is already booked.");
			}
			Booking savedBooking = bookingRepository.save(booking);
			logger.info("Booking successful: {}", savedBooking);
			return savedBooking;

		} catch (DataIntegrityViolationException e) {
			logger.error("Invalid booking details: {}", e.getMessage());
			throw new InvalidBookingDetailsException("The customer ID is invalid or does not exist.");
		}
	}

	public List<Booking> getBookingsByCustomerId(Integer customerId) {
		List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
		if (bookings.isEmpty()) {
			logger.warn("No bookings found for customer ID: {}", customerId);
			throw new BookingNotFoundException("No bookings found for customer ID: " + customerId);
		}
		logger.info("Retrieved {} bookings for customer ID: {}", bookings.size(), customerId);
		return bookings;
	}
}
