package com.example.demo.Accounts.Spaces.Services;

import com.example.demo.Accounts.Request.SpaceRequest;
import com.example.demo.Accounts.Spaces.Models.Space;
import com.example.demo.Accounts.Spaces.Repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {
    @Autowired
    private SpaceRepository repository;

    public List<Space> findAll() {
        return repository.findAll();
    }

    public Optional<Space> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<Space> findByUsertype(String usertype) {
        return repository.findByUserType(usertype);
    }

    public Optional<Space> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean existsByUserName(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Space create(SpaceRequest request) {
        Space space = new Space();
        space.setUsername(request.getUsername());
        space.setEmail(request.getEmail());
        space.setPassword(request.getPassword());
        space.setPhone(request.getPhone());
        return repository.save(space);
    }
}
