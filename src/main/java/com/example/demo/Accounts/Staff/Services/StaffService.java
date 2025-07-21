package com.example.demo.Accounts.Staff.Services;

import com.example.demo.Accounts.Staff.Models.Staff;
import com.example.demo.Accounts.Staff.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository repository;

    public List<Staff> findAll() {
        return repository.findAll();
    }

    public Optional<Staff> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<Staff> findByUsertype(String usertype) {
        return repository.findByUserType(usertype);
    }

    public Optional<Staff> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean existsByUserName(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Staff create(Staff staff) {
        return repository.save(staff);
    }
}
