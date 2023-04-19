package com.busstation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.busstation.payload.request.DashboardRequest;
import com.busstation.payload.response.dashboard.YearlyRevenueResponse;
import com.busstation.services.DashboardService;

@RestController
@RequestMapping("/api/v1/dashboards")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@GetMapping("/revenue-year")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public YearlyRevenueResponse getTotalRevenue(@RequestBody DashboardRequest request){
		return dashboardService.getRevenueDataForYear(request.getYear());
	}

	@PostMapping
	public ResponseEntity<?> statistics(@RequestBody DashboardRequest dashboardRequest){
		return new ResponseEntity<>(dashboardService.statistic(dashboardRequest), HttpStatus.OK);
	}

	@PostMapping("/date")
	public ResponseEntity<?> statisticsByDate(@RequestBody DashboardByDateRequest dashboardByDateRequest){
		return new ResponseEntity<>(dashboardService.statistics(dashboardByDateRequest),HttpStatus.OK);
	}
}
