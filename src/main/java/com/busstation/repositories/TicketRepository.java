package com.busstation.repositories;

import com.busstation.entities.Chair;
import com.busstation.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
