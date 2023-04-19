package com.busstation.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busstation.payload.response.dashboard.YearlyRevenueResponse;
import com.busstation.repositories.OrderDetailRepository;
import com.busstation.repositories.OrderRepository;
import com.busstation.services.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public int getTotalRevenueForMonth(int month, int year) {
		double sum = 0.0;
		List<Double> lstPriceForMonth = new ArrayList<>();
		lstPriceForMonth = orderDetailRepository.getPriceByMonthAndUpdateAt(month, year);
		if(lstPriceForMonth == null || lstPriceForMonth.isEmpty()) {
			lstPriceForMonth = orderDetailRepository.getPriceByMonthAndCreateAt(month, year);
			if(lstPriceForMonth == null || lstPriceForMonth.isEmpty()) {
				return 0;
			}
		}		
		for(Double price: lstPriceForMonth) {
			sum += price;
		}
		int result = (int) sum;
		return result;
	}

	public int getTotalRevenueForYear(int year) {
		double sum = 0.0;
		List<Double> lstPrice = new ArrayList<>();
		lstPrice = orderDetailRepository.getPriceByYearAndUpdateAt(year);
		if(lstPrice == null || lstPrice.isEmpty()) {
			lstPrice = orderDetailRepository.getPriceByYearAndCreateAt(year);
			if(lstPrice == null || lstPrice.isEmpty()) {
				return 0;
			}
		}		
		for(Double price: lstPrice) {
			sum += price;
		}
		int result = (int) sum;
		return result;
	}
	
	public List<Integer> getOrderForMonths(int year) {
		
		List<Integer> lstOrder = new ArrayList<>();
		for(int i = 1; i<=12; i++) {
			lstOrder.add(orderRepository.countOrdersByMonthAndYear(i, year));
		}
		return lstOrder;
	}
	
	public int getTotalOrderForYear(int year) {
		List<Integer> lstOrder = getOrderForMonths(year);
		int sum = 0;
		for(Integer i:lstOrder) {
			sum += i;
		}
		return sum;
	}

	public List<Integer> getRevenueForMonths(int year) {
		List<Integer> lstPrice = new ArrayList<>();
		for(int i = 1; i<=12; i++) {
			lstPrice.add(getTotalRevenueForMonth(i, year));
		}
		return lstPrice;
	}

	@Override
	public YearlyRevenueResponse getRevenueDataForYear(int year) {
		YearlyRevenueResponse yrr = new YearlyRevenueResponse();
		yrr.setRevenue(getRevenueForMonths(year));
		yrr.setOrder(getOrderForMonths(year));
		yrr.setTotalRevenue(getTotalRevenueForYear(year));
		yrr.setTotalOrder(getTotalOrderForYear(year));
		return yrr;
	}
		
}
