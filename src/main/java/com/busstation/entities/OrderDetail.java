package com.busstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_detail_id")
    private String orderDetailId;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chair_id")
    private Chair chair;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "price_id")
//    private Price price;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createdAt;
    protected void onCreate() { createdAt = new Date(); }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date updatedAt;
}
