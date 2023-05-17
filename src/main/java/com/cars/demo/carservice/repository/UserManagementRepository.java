package com.cars.demo.carservice.repository;

import com.cars.demo.carservice.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
