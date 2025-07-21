package com.example.demo.Accounts.Spaces.Repository;

import com.example.demo.Accounts.Spaces.Models.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Space> findByUsername(String username);
    Optional<Space> findByUserType(String userType);
    Optional<Space> findByEmail(String email);
}
