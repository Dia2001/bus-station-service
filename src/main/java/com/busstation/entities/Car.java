package com.busstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "car_id",length = 36, nullable = false)
    private String carId;
    @Column(name = "status", nullable = false)
    private Boolean status;
    @Column(name = "car_number", nullable = false, length = 11)
    private int carNumber;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp //Annotation of Hibernate to automatically save the current time when the object is created
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chair> chairs;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trips;

}
