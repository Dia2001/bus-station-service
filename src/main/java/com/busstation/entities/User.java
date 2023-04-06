package com.busstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tbl_userr")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id", length = 36, nullable = false)
    private String userId;

   // @Column(name = "account_id", length = 36, nullable = false,unique = true)
    //private String accountId;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 12, nullable = false,unique = true)
    private  String phoneNumber;

    @Column(name = "email", length = 50, nullable = false,unique = true)
    private  String email;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "status")
    private boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createdDate;

    protected void onCreate() { createdDate = new Date(); }
    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "account_id")
    private Account account;

    @OneToMany (mappedBy ="user", fetch = FetchType.LAZY)
    public Set<Leave> leaves = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Employee employee;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Trip> trips = new HashSet<>();

    @OneToMany (mappedBy ="user", fetch = FetchType.LAZY)
    public Set<Order> orders = new HashSet<>();


    public boolean getStatus() {
        return status;
    }
}
