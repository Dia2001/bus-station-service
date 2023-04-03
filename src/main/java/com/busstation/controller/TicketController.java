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

import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;
import com.busstation.services.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createTicket(@RequestBody TicketRequest ticketRequest) {
		TicketResponse ticketResponse = ticketService.addTicket(ticketRequest);
		return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
	}

	@PutMapping("/update/{ticketId}")
	public ResponseEntity<?> updateTicket(@RequestBody TicketRequest ticketRequest, @PathVariable("ticketId") String ticketId) {
		ticketService.updateTicket(ticketId, ticketRequest);
		return new ResponseEntity<>("Updated !!!", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{ticketId}")
	public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") String ticketId) {
		if (ticketService.deleteTicket(ticketId)) {
			return new ResponseEntity<>("Deleted !!!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Delete failed !!!", HttpStatus.BAD_GATEWAY);
	}
}
