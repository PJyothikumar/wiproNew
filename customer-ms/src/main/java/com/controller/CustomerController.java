package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RoomDTO;
import com.exceptions.CustomException;
import com.exceptions.CustomException.CustomerNotFoundException;
import com.exceptions.CustomException.NoRoomsFoundException;
import com.model.Customers;
import com.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@PostMapping
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customers customer) {
		try {
			Customers registeredCustomer = customerService.registerCustomer(customer);
			logger.info("Customer registered successfully: {}", registeredCustomer);
			return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Failed to register customer: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Failed to register customer: Mail id already registered.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginCustomer(@Valid @RequestBody Map<String, String> loginData)
			throws CustomerNotFoundException {
		String email = loginData.get("email");
		String password = loginData.get("password");

		Optional<Customers> customerOpt = customerService.loginCustomer(email, password);
		if (customerOpt.isPresent()) {
			return ResponseEntity.ok("Login successful! You can now check room availability.");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	}

	@GetMapping("/rooms")
	public ResponseEntity<?> getRoomsByLocation(@RequestParam("location") String location) throws CustomException {
		try {
			logger.info("Request to fetch rooms for location: {}", location);
			List<RoomDTO> rooms = customerService.getRoomsByLocation(location);
			return new ResponseEntity<>(rooms, HttpStatus.OK);
		} catch (NoRoomsFoundException e) {
			logger.error("No rooms found for location: {}", location);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", "No rooms found", "message", e.getMessage()));
		}
	}

}
