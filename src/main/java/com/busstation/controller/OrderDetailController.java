package com.busstation.controller;

import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.OrderDetailResponse;
import com.busstation.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "orderDetailAPIofWeb")
@RequestMapping("api/v1/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/getAllOrderDetail")
    public ResponseEntity<?> getAllOrderDetail(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Page<OrderDetailResponse> orderDetailPage = orderDetailService.getAllOrderDetail(pageNo, pageSize);
        return new ResponseEntity<>(orderDetailPage, HttpStatus.OK);
    }

    @GetMapping("/user/getAllOrderDetail/{user_id}")
    public ResponseEntity<?> getAllOrderDetailByUser(@PathVariable("user_id") String userId,
                                                     @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Page<OrderDetailResponse> orderDetailByUserPage = orderDetailService.getAllOrderDetailByUser(userId, pageNo, pageSize);
        return new ResponseEntity<>(orderDetailByUserPage, HttpStatus.OK);
    }

    @PutMapping("/update/{id_order_detail}")
    public ResponseEntity<?> updateTrip(@RequestBody OrderDetailRequest OrderDetailRequest,
                                        @PathVariable("id_order_detail") String orderDetailId) {

        OrderDetailResponse orderDetail = orderDetailService.updateOrderDetail(orderDetailId, OrderDetailRequest);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }

}
