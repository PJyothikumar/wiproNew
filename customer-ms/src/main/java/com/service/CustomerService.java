package com.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.RoomDTO;
import com.exceptions.CustomException;
import com.exceptions.CustomException.CustomerNotFoundException;
import com.exceptions.CustomException.InvalidCredentialsException;
import com.exceptions.CustomException.NoRoomsFoundException;
import com.model.Customers;
import com.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerFeignClient customerFeignClient;

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	public Customers registerCustomer(Customers customer) {
		logger.info("Attempting to register a new customer with email: {}", customer.getEmail());
		Customers registeredCustomer = customerRepository.save(customer);
		logger.info("Customer registered successfully with email: {}", registeredCustomer.getEmail());
		return registeredCustomer;
	}

	public Optional<Customers> loginCustomer(String email, String password) throws CustomerNotFoundException {
		logger.info("Attempting to log in customer with email: {}", email);
		Optional<Customers> customerOpt = customerRepository.findByEmail(email);
		if (customerOpt.isEmpty()) {
			logger.warn("Customer with email {} not found", email);
			throw new CustomerNotFoundException("Customer with email " + email + " not found");
		}
		if (!password.equals(customerOpt.get().getPassword())) {
			logger.warn("Invalid credentials for email: {}", email);
			throw new InvalidCredentialsException("Invalid email or password");
		}
		logger.info("Login successful for customer with email: {}", email);
		return customerOpt;
	}

	public List<RoomDTO> getRoomsByLocation(String location) throws CustomException {
		logger.info("Fetching rooms for location: {}", location);
		List<RoomDTO> rooms = customerFeignClient.getRoomsByLocation(location);

		if (rooms == null || rooms.isEmpty()) {
			logger.warn("No rooms found in the location: {}", location);
			throw new NoRoomsFoundException("No rooms found in the location: " + location);
		}

		logger.info("Found {} rooms in location: {}", rooms.size(), location);
		return rooms;
	}

}
