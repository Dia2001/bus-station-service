package com.busstation.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_trip")
@Data
public class Trip {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "trip_id",length = 36)
    private String tripId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car;
    @ManyToMany(mappedBy = "trip")
    private Set<User> users = new HashSet<>();
    @Column(name = "province_start", nullable = false, length = 20)
    private String provinceStart;
    @Column(name = "province_end", nullable = false, length = 20)
    private String provinceEnd;
    @Column(name = "time_start", nullable = false)
    private Date timeStart;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createAt;
    protected void onCreate() { createAt = new Date(); }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;
}
