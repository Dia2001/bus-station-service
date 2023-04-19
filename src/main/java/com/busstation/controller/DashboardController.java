package com.busstation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busstation.payload.request.DashboardByDateRequest;
import com.busstation.payload.request.DashboardRequest;
import com.busstation.services.DashboardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController(value = "dashboardAPIofWeb")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
	@Autowired
	private DashboardService dashboardService;
	
	@PostMapping
	public ResponseEntity<?> statistics(@RequestBody DashboardRequest dashboardRequest){
		return new ResponseEntity<>(dashboardService.statistic(dashboardRequest),HttpStatus.OK);
	}
	
	@PostMapping("/date")
	public ResponseEntity<?> statisticsByDate(@RequestBody DashboardByDateRequest dashboardByDateRequest){
		return new ResponseEntity<>(dashboardService.statistics(dashboardByDateRequest),HttpStatus.OK);
	}
}
