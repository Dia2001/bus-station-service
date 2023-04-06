package com.busstation.services;

import com.busstation.entities.Ticket;
import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;
import org.springframework.data.domain.Page;

public interface TicketService {
    TicketResponse addTicket(TicketRequest request);

    boolean updateTicket(String ticketId, TicketRequest request);

    boolean deleteTicket(String ticketId);

    Page<Ticket> getTicketPagination(int pageNumber, int pageSize);

    Page<TicketResponse> searchTicket(TicketRequest ticketRequest, int pageNumber, int pageSize);
}
