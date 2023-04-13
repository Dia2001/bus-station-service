package com.busstation.controller;

import com.busstation.payload.request.UserRequest;
import com.busstation.payload.response.ApiResponse;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:9999")
@RestController(value = "userAPIofWeb")
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	public ResponseEntity<?> getAll(@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return new ResponseEntity<>(userService.getAlL(keyword, pageNumber, pageSize), HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> update(@PathVariable("userId") String userId,
			@RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(userService.edit(userId, userRequest), HttpStatus.OK);
	}

	@PutMapping("/status/{userId}")
	public ResponseEntity<ApiResponse> setStatus(@PathVariable("userId") String userId) {
		return new ResponseEntity<>(userService.setStatus(userId), HttpStatus.OK);
	}

	@PutMapping("/auth/status/{userId}")
	public ResponseEntity<ApiResponse> setStatusUser(@PathVariable("userId") String userId) {
		return new ResponseEntity<>(userService.setStatus(userId), HttpStatus.OK);
	}
}
