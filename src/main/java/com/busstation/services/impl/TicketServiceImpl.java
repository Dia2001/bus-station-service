package com.busstation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.busstation.entities.Ticket;
import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;
import com.busstation.repositories.TicketRepository;
import com.busstation.services.TicketService;

@Component
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public TicketResponse addTicket(TicketRequest request) {
		Ticket ticket = new Ticket();
		ticket.setAddressEnd(request.getAddressEnd());
		ticket.setAddressStart(request.getAddressStart());
		ticket.setPrice(request.getPrice());
		ticketRepository.save(ticket);

		Ticket newTicket = ticketRepository.save(ticket);
		TicketResponse ticketResponse = new TicketResponse();
		ticketResponse.setTicketId(newTicket.getTicketId());
		ticketResponse.setAddressStart(newTicket.getAddressStart());
		ticketResponse.setAddressEnd(newTicket.getAddressEnd());
		ticketResponse.setPrice(newTicket.getPrice());

		return ticketResponse;
	}

	@Override
	public boolean updateTicket(String ticketId, TicketRequest request) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new RuntimeException("Ticket does not exist"));
		ticket.setAddressEnd(request.getAddressEnd());
		ticket.setAddressStart(request.getAddressStart());
		ticket.setPrice(request.getPrice());
		ticketRepository.save(ticket);
		
		return true;
	}

	@Override
	public boolean deleteTicket(String ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new RuntimeException("Ticket does not exist"));
		ticketRepository.delete(ticket);
		
		return true;
	}

}
