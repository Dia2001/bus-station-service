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



//    private List<Seat> seats = Arrays.asList(
//            new Seat(1, true),
//            new Seat(2, true),
//            new Seat(3, true),
//            new Seat(4, true),
//            new Seat(5, true),
//            new Seat(6, true),
//            new Seat(7, true),
//            new Seat(8, true),
//            new Seat(9, true),
//            new Seat(10, true)
//    );

    @MessageMapping("/chairs")
    @SendTo("/topic/chairs")
    public List<ChairResponse> handleChairsUpdate(@Payload String carId){
        return chairService.showAllChair(carId);
    }
//    @MessageMapping("/seat")
//    @SendTo("/topic/seat")
//    public Seat handleSeatUpdate(@Payload  Seat seat) throws Exception {
//        for (Seat s : seats) {
//            if (s.getId() == seat.getId()) {
//                s.setAvailable(seat.isAvailable());
//                if (!seat.isAvailable()) {
//                    // disable the seat for all other clients
//                    simpMessagingTemplate.convertAndSend("/topic/disableSeat", seat);
//                }
//                break;
//            }
//        }
//        return seat;
//    }
//
//    @MessageMapping("/seats")
//    @SendTo("/topic/seats")
//    public List<Seat> handleSeatsUpdate() {
//        return seats;
//    }
}

