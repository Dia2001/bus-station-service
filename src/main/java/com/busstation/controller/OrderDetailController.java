package com.busstation.controller;

import com.busstation.entities.OrderDetail;
import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.OrderDetailResponse;
import com.busstation.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/order_detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @PutMapping("/update/{id_order_detail}")
    public ResponseEntity<?> updateTrip(@RequestBody OrderDetailRequest OrderDetailRequest,
                                                  @PathVariable("id_order_detail") String orderDetailId){

        OrderDetailResponse orderDetail = orderDetailService.updateOrderDetail(orderDetailId,OrderDetailRequest);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }

}
