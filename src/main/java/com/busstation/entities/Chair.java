package com.busstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_chair")
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chair_id")
    private String chairId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "car_id")
//    private Car car;

    @Column(name = "chair_number", length = 11, nullable = false)
    private int chairNumber;
}
