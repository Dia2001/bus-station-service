package com.busstation.services;

import com.busstation.payload.response.ChairResponse;
import org.springframework.data.domain.Page;


public interface ChairService {
    Page<ChairResponse> showAllChair(String carId, int pageNumber, int pageSize);

    ChairResponse searchChairNumber(String carId, int chairNumber);
}
