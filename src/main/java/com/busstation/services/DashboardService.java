package com.busstation.services;

import java.util.Map;

import com.busstation.payload.request.DashboardByDateRequest;
import com.busstation.payload.request.DashboardRequest;

public interface DashboardService {
	Map<String, Object> statistic(DashboardRequest dashboardRequest);
	
	Map<String, Object> statistics(DashboardByDateRequest dashboardByDateRequest);
}
