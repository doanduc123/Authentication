package com.example.demo.Core.Services;

import com.example.demo.Accounts.Consumers.Repositories.ConsumerRepository;
import com.example.demo.Accounts.Spaces.Repository.SpaceRepository;
import com.example.demo.Accounts.Staff.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByUsernameInternal(username);
    }

    public UserDetails loadUserByEmail(String email) {
        return loadUserByEmailInternal(email);
    }

    private UserDetails loadUserByUsernameInternal(String username) {
        return staffRepository.findByUsername(username)
                .map(staff -> User.withUsername(staff.getUsername())
                        .password(staff.getPassword())
                        .roles(staff.getUserType())
                        .build())
                .or(() -> spaceRepository.findByUsername(username)
                        .map(space -> User.withUsername(space.getUsername())
                                .password(space.getPassword())
                                .roles(space.getUserType())
                                .build()))
                .or(() -> consumerRepository.findByUsername(username)
                        .map(consumer -> User.withUsername(consumer.getUsername())
                                .password(consumer.getPassword())
                                .roles(consumer.getUserType())
                                .build()))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    private UserDetails loadUserByEmailInternal(String email) {
        return staffRepository.findByEmail(email)
                .map(staff -> User.withUsername(staff.getUsername())
                        .password(staff.getPassword())
                        .roles(staff.getUserType())
                        .build())
                .or(() -> spaceRepository.findByEmail(email)
                        .map(space -> User.withUsername(space.getUsername())
                                .password(space.getPassword())
                                .roles(space.getUserType())
                                .build()))
                .or(() -> consumerRepository.findByEmail(email)
                        .map(consumer -> User.withUsername(consumer.getUsername())
                                .password(consumer.getPassword())
                                .roles(consumer.getUserType())
                                .build()))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    }
}
