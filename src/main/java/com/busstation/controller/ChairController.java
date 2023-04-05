package com.busstation.controller;

import com.busstation.services.ChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chairs")
public class ChairController {

    @Autowired
    private ChairService chairService;

    //Show all chair by Car
    @GetMapping("/{carId}")
    public ResponseEntity<?> showAllChairNumberByCar(
            @PathVariable("carId") String carId,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

            return new ResponseEntity<>(chairService.showAllChair(carId, pageNo, pageSize), HttpStatus.OK);
    }

    //Search ChairNumber by CarId
    @GetMapping("/{carId}/{chairNumber}")
    public ResponseEntity<?> searchChairNumber(
            @PathVariable("carId") String carId,
            @PathVariable("chairNumber") int chairNumber
    ) {
        return new ResponseEntity<>(chairService.searchChairNumber(carId, chairNumber), HttpStatus.OK);

    }
}
