package com.busstation.repositories;

import com.busstation.entities.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChairRepository extends JpaRepository<Chair, String> {
}
