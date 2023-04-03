package com.busstation.services;

import org.springframework.stereotype.Service;

import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;

@Service
public interface TicketService {
	TicketResponse addTicket(TicketRequest request);
	boolean updateTicket(String ticketId , TicketRequest request);
	boolean deleteTicket(String ticketId);
}
