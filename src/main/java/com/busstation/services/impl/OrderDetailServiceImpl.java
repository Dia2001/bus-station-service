package com.busstation.services.impl;

import com.busstation.entities.*;
import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.*;
import com.busstation.repositories.*;
import com.busstation.services.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ChairRepository chairRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderDetailResponse updateOrderDetail(String orderDetailId, OrderDetailRequest orderDetailRequest) {

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(()->new EntityNotFoundException("Order Detail does not exist"));

        Chair chair = chairRepository.findById(orderDetailRequest.getChairId()).orElseThrow(()->new EntityNotFoundException("chair does not exist"));

        Order order = orderRepository.findById(orderDetail.getOrder().getOrderID()).orElseThrow(()->new EntityNotFoundException("Order does not exist"));

        Ticket ticket = ticketRepository.findById(orderDetailRequest.getTicketId()).orElseThrow(()->new EntityNotFoundException("Ticker does not exist"));

        orderDetail.setStatus(orderDetailRequest.getStatus());
        orderDetail.setChair(chair);
        orderDetail.setOrder(order);
        orderDetail.setTicket(ticket);
        orderDetail.setUpdatedAt(new Date());

        orderDetailRepository.save(orderDetail);

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setOrderDetailId(orderDetail.getOrderDetailId());
        orderDetailResponse.setStatus(orderDetail.getStatus());
        orderDetailResponse.setChair(setupChairResponse(chair));
        orderDetailResponse.setOrder(setupOrderResponse(order));
        orderDetailResponse.setTicket(setupTicketResponse(ticket));


        return orderDetailResponse;
    }
    public UserResponse setupUserResponse(Order order){
        User user = userRepository.findById(order.getUser().getUserId()).orElseThrow(()->new EntityNotFoundException("User does not exist"));

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setStatus(user.getStatus());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());

        return userResponse;
    }

    public ChairResponse  setupChairResponse(Chair chair){

        ChairResponse chairResponse = new ChairResponse();
        chairResponse.setChairId(chair.getChairId());
        chairResponse.setChairNumber(chair.getChairNumber());
        chairResponse.setCarId(chair.getCar().getCarId());

        return chairResponse;
    }

    public OrderResponse  setupOrderResponse(Order order){

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderID(order.getOrderID());
        orderResponse.setUser(setupUserResponse(order));

        return orderResponse;
    }

    public TicketResponse  setupTicketResponse(Ticket ticket){

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketId(ticket.getTicketId());
        ticketResponse.setAddressStart(ticket.getAddressStart());
        ticketResponse.setAddressEnd(ticket.getAddressEnd());
        ticketResponse.setPrice(ticket.getPrice());

        return ticketResponse;
    }
}
