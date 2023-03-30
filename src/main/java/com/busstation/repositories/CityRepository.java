package com.busstation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busstation.entities.City;

public interface CityRepository extends JpaRepository<City, Integer>{

}
