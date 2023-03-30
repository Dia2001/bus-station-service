package com.busstation.repositories;

import com.busstation.entities.Role;
import com.busstation.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
}
