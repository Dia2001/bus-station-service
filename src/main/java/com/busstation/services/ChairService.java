package com.busstation.services;


import com.busstation.payload.response.ChairResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.busstation.payload.request.ChairRequest;
import com.busstation.payload.response.ChairResponse;

public interface ChairService {
    Page<ChairResponse> showAllChair(String carId, int pageNumber, int pageSize);
    ChairResponse searchChairNumber(String carId, int chairNumber);
	ChairResponse addChair(ChairRequest request);
	boolean updateChair(String chairId , ChairRequest request);
	boolean deleteChair(String chairId);
}
