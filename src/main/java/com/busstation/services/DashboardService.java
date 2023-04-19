package com.busstation.services;

import com.busstation.payload.response.dashboard.YearlyRevenueResponse;

public interface DashboardService {
				
	public YearlyRevenueResponse getRevenueDataForYear(int year);
}
