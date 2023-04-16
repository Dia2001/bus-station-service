package com.busstation.repositories;

import com.busstation.entities.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    Page<OrderDetail> findAll(Pageable pageable);

    List<OrderDetail> findByOrder_OrderID(String orderId);

    Page<OrderDetail> findByOrder_OrderID(String orderId, Pageable pageable);

    @Query(value = "FROM OrderDetail od JOIN od.order o JOIN o.user u WHERE u.userId = :userId")
    Page<OrderDetail> findAllByUserId(@Param("userId") String userId, Pageable pageable);

}
