package com.busstation.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.busstation.entities.Ticket;
import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;

@Service
public interface TicketService {
	TicketResponse addTicket(TicketRequest request);
	boolean updateTicket(String ticketId , TicketRequest request);
	boolean deleteTicket(String ticketId);
	Page<Ticket> getTicketPagination(int pageNumber , int pageSize);
}
