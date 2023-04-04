package com.busstation.services.impl;

import com.busstation.entities.*;
import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.*;
import com.busstation.repositories.*;
import com.busstation.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ChairRepository chairRepository;


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TripRepository tripRepository;



    @Override
    public OrderResponse createOrder(String userId, OrderDetailRequest orderDetailRequest) {

        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User does not exist"));

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setStatus(user.getStatus());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());

        Order order1 = new Order();
        order1.setUser(user);

        Order newOrder = orderRepository.save(order1);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderID(newOrder.getOrderID());
        orderResponse.setUser(userResponse);


        Chair chair = chairRepository.findById(orderDetailRequest.getChairId()).orElseThrow(()->new EntityNotFoundException("chair does not exist"));

        Ticket ticket = ticketRepository.findById(orderDetailRequest.getTicketId()).orElseThrow(()->new EntityNotFoundException("Ticker does not exist"));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(orderDetailRequest.getStatus());
        orderDetail.setChair(chair);
        orderDetail.setOrder(newOrder);
        orderDetail.setTicket(ticket);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        addUserToTrip(chair.getCar().getTrips().getTripId(),userId);


        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setOrderDetailId(newOrderDetail.getOrderDetailId());
        orderDetailResponse.setStatus(newOrderDetail.getStatus());
        orderDetailResponse.setChair(setupChairResponse(chair));
        orderDetailResponse.setOrder(setupOrderResponse(newOrder));
        orderDetailResponse.setTicket(setupTicketResponse(ticket));

        orderResponse.setOrderDetail(orderDetailResponse);


        return orderResponse;
    }

    public void addUserToTrip(String tripId, String userId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        trip.getUsers().add(user);
        tripRepository.save(trip);
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

    public ChairResponse setupChairResponse(Chair chair){

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

    public TicketResponse setupTicketResponse(Ticket ticket){

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketId(ticket.getTicketId());
        ticketResponse.setAddressStart(ticket.getAddressStart());
        ticketResponse.setAddressEnd(ticket.getAddressEnd());
        ticketResponse.setPrice(ticket.getPrice());

        return ticketResponse;
    }
}
