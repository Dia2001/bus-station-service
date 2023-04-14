package com.busstation.controller.socket;

import com.busstation.dto.ChairDto;
import com.busstation.payload.response.ChairResponse;
import com.busstation.services.ChairService;
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
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;



    @MessageMapping("/chairs")
    @SendTo("/topic/chairs")
    public List<ChairResponse> handleChairsUpdate(@Payload String carId){
        return chairService.showAllChair(carId);
    }

    @MessageMapping("/chair")
    @SendTo("/topic/chair")
    public ChairResponse handleSeatUpdate(@Payload  String chairId) throws Exception {
        ChairResponse chairResponse=chairService.updateStatus(chairId);
        simpMessagingTemplate.convertAndSend("/topic/disableSeat",chairResponse);
        return  chairResponse;
    }
}

