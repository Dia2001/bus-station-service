package com.busstation.controller;

import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;
import com.busstation.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "ticketAPIofWeb")
@RequestMapping("/api/v1/tickets")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> searchTicket(@RequestBody TicketRequest ticketRequest,
										  @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
		Page<TicketResponse> ticketResponse = ticketService.searchTicket(ticketRequest, pageNumber, pageSize);
		return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
	}

	@PostMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createTicket(@RequestBody TicketRequest ticketRequest) {
		TicketResponse ticketResponse = ticketService.addTicket(ticketRequest);
		return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
	}

	@PutMapping("/{ticketId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateTicket(@RequestBody TicketRequest ticketRequest,
										  @PathVariable("ticketId") String ticketId) {
		ticketService.updateTicket(ticketId, ticketRequest);
		return new ResponseEntity<>("Updated !!!", HttpStatus.OK);
	}

	@DeleteMapping("/{ticketId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") String ticketId) {
		if (ticketService.deleteTicket(ticketId)) {
			return new ResponseEntity<>("Deleted !!!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Delete failed !!!", HttpStatus.BAD_GATEWAY);
	}

}
