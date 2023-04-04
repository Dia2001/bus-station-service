package com.busstation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busstation.payload.request.ChairRequest;
import com.busstation.payload.response.ChairResponse;
import com.busstation.services.ChairService;

@RestController
@RequestMapping("/api/v1/chairs")
public class ChairController {
	@Autowired
	private ChairService chairService;

	@PostMapping("/create")
	public ResponseEntity<?> createChair(@RequestBody ChairRequest chairRequest) {
		ChairResponse chairResponse = chairService.addChair(chairRequest);
		return new ResponseEntity<>(chairResponse, HttpStatus.CREATED);
	}

	@PutMapping("/update/{chairId}")
	public ResponseEntity<?> updateChair(@RequestBody ChairRequest request, @PathVariable("chairId") String chairId) {
		chairService.updateChair(chairId, request);
		return new ResponseEntity<>("Updated !!!", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{chairId}")
	public ResponseEntity<?> deleteChiar(@PathVariable("chairId") String chairId) {
		if(chairService.deleteChair(chairId)) {
			return new ResponseEntity<>("Deleted !!!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Delete failed !!!", HttpStatus.BAD_GATEWAY);
	}
}
