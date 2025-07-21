package com.example.demo.Accounts.Staff.Repository;

import com.example.demo.Accounts.Staff.Models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Staff> findByUsername(String username);
    Optional<Staff> findByUserType(String userType);
    Optional<Staff> findByEmail(String email);
}
