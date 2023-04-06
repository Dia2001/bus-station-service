package com.busstation.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_trip")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "trip_id",length = 36)
    private String tripId;

    @Column(name = "province_start", nullable = false, length = 50)
    private String provinceStart;

    @Column(name = "province_end", nullable = false, length = 50)
    private String provinceEnd;

    @Column(name = "time_start", nullable = false)
    private Date timeStart;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "trip_user",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "trips", fetch = FetchType.LAZY)
    private List<Car> cars;
}
