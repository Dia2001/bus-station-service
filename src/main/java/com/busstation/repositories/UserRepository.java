package com.busstation.repositories;

import com.busstation.entities.Trip;
import com.busstation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
