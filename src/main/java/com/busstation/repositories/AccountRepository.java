package com.busstation.repositories;

import com.busstation.entities.Account;
import com.busstation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
