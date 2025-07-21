package com.example.demo.Core.Services;

import com.example.demo.Accounts.Consumers.Models.Consumer;
import com.example.demo.Accounts.Consumers.Repositories.ConsumerRepository;
import com.example.demo.Accounts.Spaces.Models.Space;
import com.example.demo.Accounts.Spaces.Repository.SpaceRepository;
import com.example.demo.Accounts.Staff.Models.Staff;
import com.example.demo.Accounts.Staff.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final SpaceRepository spaceRepository;
    private final StaffRepository staffRepository;
    private final ConsumerRepository consumerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email not found from OAuth2 provider");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        Optional<Space> optionalSpace = spaceRepository.findByEmail(email);
        if (optionalSpace.isPresent()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_SPACE"));
            return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
        }

        Optional<Staff> optionalStaff = staffRepository.findByEmail(email);
        if (optionalStaff.isPresent()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STAFF"));
            return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
        }

        Optional<Consumer> optionalConsumer = consumerRepository.findByEmail(email);
        if (optionalConsumer.isPresent()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CONSUMER"));
            return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
        }

        Consumer newConsumer = new Consumer();
        newConsumer.setEmail(email);
        newConsumer.setUsername(name != null ? name : email);
        newConsumer.setPassword(passwordEncoder.encode("oauth2-user-" + email));
        newConsumer.setStatus("ACTIVE");
        newConsumer.setUserType("CONSUMER");
        consumerRepository.save(newConsumer);

        authorities.add(new SimpleGrantedAuthority("ROLE_CONSUMER"));
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
    }
}
