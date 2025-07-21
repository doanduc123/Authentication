package com.example.demo.Accounts.Consumers.Repositories;

import com.example.demo.Accounts.Consumers.Models.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Consumer> findByUsername(String username);
    Optional<Consumer> findByUserType(String userType);
    Optional<Consumer> findByEmail(String email);
}
