package com.example.demo.Accounts.Consumers.Repositories;

import com.example.demo.Accounts.Consumers.Models.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

}
