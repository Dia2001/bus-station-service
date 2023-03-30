package com.busstation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tbl_car")
@Data
public class Car {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "car_id",length = 36, nullable = false)
    private String carId;
    @Column(name = "status", nullable = false)
    private Boolean status;
    @Column(name = "car_number", nullable = false, length = 11)
    private int carNumber;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createAt;
    protected void onCreate() { createAt = new Date(); }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;

    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chair> chairs;
    @JsonIgnore
    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY)
    private Trip trip;


    public List<Chair> getChairs() {

        return chairs == null ? null : new ArrayList<>(chairs);
    }
    public void setChairs(List<Chair> chairs) {

        if (chairs == null) {
            this.chairs = null;
        } else {
            this.chairs = Collections.unmodifiableList(chairs);
        }
    }
}
