package com.busstation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busstation.entities.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
