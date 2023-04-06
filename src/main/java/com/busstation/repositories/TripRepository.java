package com.busstation.repositories;

import com.busstation.entities.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TripRepository extends JpaRepository<Trip,String> {

    @Query(value = "FROM Trip trip WHERE date(trip.timeStart) >= date(CURRENT_TIMESTAMP)")
    Page<Trip> findAllTrips(Pageable pageable);

    @Query(value = "FROM Trip trip WHERE date(trip.timeStart) >= date(CURRENT_TIMESTAMP) AND trip.provinceStart = :province_start AND trip.provinceEnd = :province_end")
    Page<Trip> findByProvinceStartAndProvinceEnd(@Param("province_start") String provinceStart, @Param("province_end") String provinceEnd, Pageable pageable);

    @Query("FROM Trip trip WHERE date(trip.timeStart) >= date(CURRENT_TIMESTAMP) AND trip.provinceStart = :provinceStart AND trip.provinceEnd = :provinceEnd" +
            " AND date(trip.timeStart) = date(:dateTime)")
    Page<Trip> findByProvinceStartAndProvinceEndAndDateTime(@Param("provinceStart") String provinceStart, @Param("provinceEnd") String provinceEnd,
                                                            @Param("dateTime") Date dateTime, Pageable pageable);

}
