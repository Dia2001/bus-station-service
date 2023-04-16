package com.busstation.controller.socket;

import com.busstation.dto.ChairDto;
import com.busstation.payload.request.OrderRequest;
import com.busstation.payload.response.ChairResponse;
import com.busstation.payload.response.OrderResponse;
import com.busstation.services.ChairService;
import com.busstation.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class ChairControllerSocket {
    @Autowired
    private ChairService chairService;
    OrderService orderService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;



    @MessageMapping("/chairs")
    @SendTo("/topic/chairs")
    public List<ChairResponse> handleChairsUpdate(@Payload String carId){
        return chairService.showAllChair(carId);
    }

    @MessageMapping("/chair")
    @SendTo("/topic/chair")
    public OrderResponse handleSeatUpdate(@Payload OrderRequest orderRequest) throws Exception {
        OrderResponse orderResponse=orderService.createOrder(orderRequest);
        simpMessagingTemplate.convertAndSend("/topic/disableSeat",orderResponse);
        return  orderResponse;
    }
}

