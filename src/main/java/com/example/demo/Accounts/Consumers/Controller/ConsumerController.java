package com.example.demo.Accounts.Consumers.Controller;

import com.example.demo.Accounts.Consumers.Models.Consumer;
import com.example.demo.Accounts.Consumers.Repositories.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerRepository consumerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public Consumer createConsumer(@RequestBody Consumer consumer) {
        String hashed = passwordEncoder.encode(consumer.getPassword());
        consumer.setPassword(hashed);
        if (consumer.getUserType() == null) {
            consumer.setUserType("CONSUMER");
        }
        consumer.setUsername("123 test");
        return consumerRepository.save(consumer);
    }
}
