package com.busstation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "employee_id",length = 36)
    private String employeeId;
    @OneToOne(fetch = FetchType.LAZY) //không nạp đối tượng liên quan vào bộ nhớ cho đến khi có yêu cầu truy cập đến đối tượng
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;
    @Column(name = "dob", nullable = false)
    private Date dob;
    @Column(name = "yoe", nullable = false, length = 11)
    private int yoe;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createAt;
    protected void onCreate() { createAt = new Date(); }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;

    @JsonIgnore
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
