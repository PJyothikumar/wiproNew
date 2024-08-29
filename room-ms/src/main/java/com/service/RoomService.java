package com.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceptions.CustomException;
import com.exceptions.CustomException.RoomsNotFoundException;
import com.model.Room;
import com.repository.RoomRepository;

@Service
public class RoomService {

	private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

	@Autowired
	private RoomRepository roomRepository;

	public Optional<Room> getRoomById(Integer id) throws CustomException {
		logger.info("Fetching room with ID: {}", id);
		Optional<Room> room = roomRepository.findById(id);

		if (!room.isPresent()) {
			logger.warn("No room found for ID: {}", id);
			throw new CustomException("No room found for ID: " + id);
		}

		logger.info("Room found with ID: {}", id);
		return room;
	}

	public List<Room> getRoomsByLocation(String location) throws RoomsNotFoundException {
		logger.info("Fetching rooms for location: {}", location);
		List<Room> rooms = roomRepository.findByLocation(location);

		if (rooms.isEmpty()) {
			logger.warn("No rooms found for location: {}", location);
			throw new RoomsNotFoundException("No rooms found for location: " + location);
		}

		logger.info("Found {} rooms for location: {}", rooms.size(), location);
		return rooms;
	}
}
