package com.busstation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busstation.entities.Province;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province , Integer>{

}
