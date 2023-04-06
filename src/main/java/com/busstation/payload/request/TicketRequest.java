package com.busstation.payload.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class TicketRequest {
	private String addressStart;
	private String addressEnd;
	private BigDecimal price;
}
