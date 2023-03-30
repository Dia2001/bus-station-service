package com.busstation.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_leave")
@Data
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "leave_id",length = 36)
    private String leaveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date_start", nullable = false)
    private Date dateStart;
    @Column(name = "date_end", nullable = false)
    private Date dateEnd;

    @Column(name = "reason", nullable = false, length = 100)
    private String reason;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createAt;
    protected void onCreate() { createAt = new Date(); }

    @JsonIgnore
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
