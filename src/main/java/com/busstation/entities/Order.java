package com.busstation.entities;


import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_order")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Order  implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_id", length = 36, nullable = false)
    private String orderID;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date createdDate;

    protected void onCreate() { createdDate = new Date(); }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", nullable = true)
    private Date updateDate;
  
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
