package com.busstation.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busstation.payload.request.DashboardByDateRequest;
import com.busstation.payload.request.DashboardRequest;
import com.busstation.repositories.OrderDetailRepository;
import com.busstation.services.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public Map<String, Object> statistic(DashboardRequest dashboardRequest) {
	    List<Object[]> resultList = orderDetailRepository.getOrderDetailsByMonth(dashboardRequest.getMonth(),
	            dashboardRequest.getYear());
	    List<Integer> revenueList = new ArrayList<>();
	    List<Integer> orderList = new ArrayList<>();
	    List<Integer> dayList = new ArrayList<>();
	    BigDecimal totalRevenue = BigDecimal.ZERO;
	    int totalOrder = 0;
	    int dayOfMonth = getNumberOfDays(dashboardRequest.getYear(), dashboardRequest.getMonth());

	    for (int i = 1; i <= dayOfMonth; i++) {
	        int revenue = 0;
	        int order = 0;
	        for (Object[] obj : resultList) {
	            int day = ((Number) obj[2]).intValue();
	            if (day == i) {
	                BigDecimal revenueValue = obj[0] == null ? BigDecimal.ZERO : (BigDecimal) obj[0];
	                revenue += revenueValue.setScale(0, RoundingMode.DOWN).intValue();
	                order += ((Number) obj[1]).intValue();
	                totalRevenue = totalRevenue.add(revenueValue);
	                totalOrder += ((Number) obj[1]).intValue();
	            }
	        }
	        revenueList.add(revenue);
	        orderList.add(order);
	        dayList.add(i);
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("revenue", revenueList);
	    response.put("order", orderList);
	    response.put("day", dayList);
	    response.put("totalOrder", totalOrder);
	    response.put("totalRevenue", totalRevenue.setScale(0, RoundingMode.DOWN).intValue());
	    return response;
	}


	@Override
	public Map<String, Object> statistics(DashboardByDateRequest dashboardByDateRequest) {
		List<Object[]> resultList = orderDetailRepository.getOrderDetailsByDate(dashboardByDateRequest.getStart(),
				dashboardByDateRequest.getEnd());
	    List<Integer> revenueList = new ArrayList<>();
	    List<Integer> orderList = new ArrayList<>();
	    List<Integer> dayList = new ArrayList<>();
	    BigDecimal totalRevenue = BigDecimal.ZERO;
	    int totalOrder = 0;
	    Date startDate = dashboardByDateRequest.getStart();
	    Date endDate = dashboardByDateRequest.getEnd();

	    LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	    long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
	    int days = (int) daysBetween;

	    for (int i = 1; i <= days; i++) {
	        int revenue = 0;
	        int order = 0;
	        for (Object[] obj : resultList) {
	            int day = ((Number) obj[2]).intValue();
	            if (day == i) {
	                BigDecimal revenueValue = obj[0] == null ? BigDecimal.ZERO : (BigDecimal) obj[0];
	                revenue += revenueValue.setScale(0, RoundingMode.DOWN).intValue();
	                order += ((Number) obj[1]).intValue();
	                totalRevenue = totalRevenue.add(revenueValue);
	                totalOrder += ((Number) obj[1]).intValue();
	            }
	        }
	        revenueList.add(revenue);
	        orderList.add(order);
	        dayList.add(i);
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("revenue", revenueList);
	    response.put("order", orderList);
	    response.put("day", dayList);
	    response.put("totalOrder", totalOrder);
	    response.put("totalRevenue", totalRevenue.setScale(0, RoundingMode.DOWN).intValue());
	    return response;
	}

	public int getNumberOfDays(int year, int month) {
		YearMonth yearMonth = YearMonth.of(year, month);
		return yearMonth.lengthOfMonth();
	}

}
