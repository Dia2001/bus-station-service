package com.busstation.services;

import com.busstation.entities.OrderDetail;
import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.OrderDetailResponse;

public interface OrderDetailService {

    OrderDetailResponse updateOrderDetail(String orderDetailId, OrderDetailRequest orderDetailRequest);

}
