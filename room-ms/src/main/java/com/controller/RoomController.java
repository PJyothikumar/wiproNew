package com.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.CustomException;
import com.model.Room;
import com.service.RoomService;

import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;

	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	@GetMapping
	public ResponseEntity<List<Room>> getRoomsByLocation(@RequestParam("location") String location) {
		logger.info("Received request to fetch rooms for location: {}", location);
		List<Room> rooms = roomService.getRoomsByLocation(location);
		logger.info("Returning {} rooms for location: {}", rooms.size(), location);
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	@GetMapping("/{room_id}")
	public ResponseEntity<?> getRoomById(@PathVariable("room_id") @Positive Integer roomId) throws CustomException {
		logger.info("Received request to fetch room with ID: {}", roomId);
		Optional<Room> room = roomService.getRoomById(roomId);
		logger.info("Returning room with ID: {}", roomId);
		return new ResponseEntity<>(room, HttpStatus.OK);
	}
}
