package com.busstation.services.impl;

import com.busstation.entities.Ticket;
import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.response.TicketResponse;
import com.busstation.repositories.TicketRepository;
import com.busstation.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

// NOTE : No exception handling

@Service
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
	public TicketResponse updateTicket(String ticketId, TicketRequest request) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new RuntimeException("Ticket does not exist"));
		ticket.setAddressEnd(request.getAddressEnd());
		ticket.setAddressStart(request.getAddressStart());
		ticket.setPrice(request.getPrice());
		ticketRepository.save(ticket);

		TicketResponse ticketResponse = new TicketResponse(
				ticket.getTicketId(),ticket.getAddressStart(),ticket.getAddressEnd(),ticket.getPrice());

		return ticketResponse;
	}

	@Override
	public boolean deleteTicket(String ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new RuntimeException("Ticket does not exist"));
		ticketRepository.delete(ticket);

		return true;
	}


	@Override
	public Page<TicketResponse> searchTicket(TicketRequest ticketRequest, int pageNumber, int pageSize) {
		 Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("price").ascending());
		 if(ticketRequest.getAddressStart() == null || ticketRequest.getAddressEnd() == null ) {
			 Page<Ticket> ticket = ticketRepository.findAll(pageable);
			 Page<TicketResponse> ticketResponse = ticket.map(TicketResponse :: new);
			 return ticketResponse;
		 }
		 
		 if(ticketRequest.getPrice() == null) {
			 Page<Ticket> ticket = ticketRepository.findByAddress(ticketRequest.getAddressStart(), ticketRequest.getAddressEnd(), pageable);
			 Page<TicketResponse> ticketResponse = ticket.map(TicketResponse :: new);
			 return ticketResponse;
		 }
		 
		 Page<Ticket> ticket = ticketRepository.findByTickets(ticketRequest.getAddressStart(), ticketRequest.getAddressEnd(),ticketRequest.getPrice(), pageable);
		 Page<TicketResponse> ticketResponse = ticket.map(TicketResponse :: new);
		 return ticketResponse;
	}

}
