package com.example.demo.Core.Services;

import com.example.demo.Accounts.Consumers.Models.Consumer;
import com.example.demo.Accounts.Consumers.Repositories.ConsumerRepository;
import com.example.demo.Accounts.Spaces.Models.Space;
import com.example.demo.Accounts.Spaces.Repository.SpaceRepository;
import com.example.demo.Accounts.Staff.Models.Staff;
import com.example.demo.Accounts.Staff.Repository.StaffRepository;
import com.example.demo.Core.Utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final SpaceRepository spaceRepository;
    private final ConsumerRepository consumerRepository;
    private final StaffRepository staffRepository;
    private final JwtTokenUtil jwtUtil;

    public AuthService(SpaceRepository spaceRepository, ConsumerRepository consumerRepository,
                       StaffRepository staffRepository, JwtTokenUtil jwtUtil) {
        this.spaceRepository = spaceRepository;
        this.consumerRepository = consumerRepository;
        this.staffRepository = staffRepository;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, String> authenticateAndGenerateTokens(String username, String password) {
        Consumer consumer = consumerRepository.findByUsername(username).orElse(null);
        Space space = spaceRepository.findByUsername(username).orElse(null);
        Staff staff = staffRepository.findByUsername(username).orElse(null);

        String userType = null;
        String email = null;
        if (consumer != null) {
            userType = consumer.getUserType();
            email = consumer.getEmail();
        } else if (space != null) {
            userType = space.getUserType();
            email = space.getEmail();
        } else if (staff != null) {
            userType = staff.getUserType();
            email = staff.getEmail();
        }
        if (userType == null || email == null) {
            throw new RuntimeException("User not found");
        }

        String accessToken = jwtUtil.generateAccessToken(email, userType);
        String refreshToken = jwtUtil.generateRefreshToken(email, userType);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }
}
