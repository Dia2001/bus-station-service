package com.busstation.payload.response;

import java.math.BigDecimal;

import com.busstation.entities.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class TicketResponse {
	private String ticketId;
	private String addressStart;
	private String addressEnd;
	private BigDecimal price;
	
	public TicketResponse(Ticket ticket) {
		this.ticketId = ticket.getTicketId();
		this.addressStart = ticket.getAddressStart();
		this.addressEnd = ticket.getAddressEnd();
		this.price = ticket.getPrice();
	}
	
}
