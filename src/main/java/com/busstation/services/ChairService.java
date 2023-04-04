package com.busstation.services;

import org.springframework.stereotype.Service;

import com.busstation.payload.request.ChairRequest;
import com.busstation.payload.response.ChairResponse;

@Service
public interface ChairService {
	ChairResponse addChair(ChairRequest request);
	boolean updateChair(String chairId , ChairRequest request);
	boolean deleteChair(String chairId);
}
