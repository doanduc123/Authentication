package com.example.demo.Accounts.Consumers.Services;

import com.example.demo.Accounts.Consumers.Models.Consumer;
import com.example.demo.Accounts.Consumers.Repositories.ConsumerRepository;
import com.example.demo.Accounts.Spaces.Models.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumersService {
    @Autowired
    private ConsumerRepository repository;

    public List<Consumer> findAll() {
        return repository.findAll();
    }

    public Optional<Consumer> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<Consumer> findByUsertype(String usertype) {
        return repository.findByUserType(usertype);
    }

    public Optional<Consumer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean existsByUserName(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Consumer create(Consumer consumer) {
        return repository.save(consumer);
    }
}


