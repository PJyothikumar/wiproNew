
package com.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.config.FeignConfig;
import com.dto.RoomDTO;

@FeignClient(name = "room-ms", configuration = FeignConfig.class)
public interface CustomerFeignClient {

	@GetMapping("/rooms")
	List<RoomDTO> getRoomsByLocation(@RequestParam("location") String location);
}
