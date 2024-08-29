package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
	Optional<Customers> findByEmail(String email);
}
