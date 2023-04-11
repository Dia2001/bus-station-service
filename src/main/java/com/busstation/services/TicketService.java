package com.busstation.services;

import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;
import org.springframework.data.domain.Page;

public interface TicketService {
	TicketResponse getTicketById(String ticketId );
	TicketResponse addTicket(TicketRequest request);

	boolean updateTicket(String ticketId, TicketRequest request);

	boolean deleteTicket(String ticketId);

	Page<TicketResponse> searchTicket(TicketRequest ticketRequest, int pageNumber, int pageSize);
}
