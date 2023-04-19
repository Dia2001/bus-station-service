package com.busstation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
