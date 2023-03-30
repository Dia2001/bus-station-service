package com.busstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id", length = 36)
    private String ticketId;

    @Column(name = "address_start", length = 50, nullable = false)
    private String addressStart;
    @Column(name = "address_end", length = 50,nullable = false)
    private String addressEnd;

    @Column(name = "price", nullable = false, length = 18)
    private BigDecimal price;
}
