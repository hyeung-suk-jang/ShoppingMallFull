package com.example.parayo.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User updateFcmToken(final String email, final String fcmToken) {
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보 없음"));
        findUser.updateToken(fcmToken);
        return userRepository.save(findUser);
    }

    public User find(final Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findByEmail(final String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
