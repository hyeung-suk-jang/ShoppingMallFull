package com.example.parayo.domain.auth;

import com.example.parayo.common.ParayoException;
import com.example.parayo.domain.user.User;
import com.example.parayo.domain.user.UserRepository;
import com.example.parayo.dto.SigninRequest;
import com.example.parayo.dto.SigninResponse;
import com.example.parayo.global.utils.JwtUtil;
import com.example.parayo.global.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SigninService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SigninResponse signin(SigninRequest signinRequest) {
        Optional<User> user = userRepository.findByEmail(
                signinRequest.getEmail().toLowerCase());

        if (user.isEmpty()) {
            throw new ParayoException("로그인 정보를 확인해주세요");
        }

        if (isNotValidPassword(signinRequest.getPassword(), user.get().getPassword())) {
            throw new ParayoException("로그인 정보를 확인해주세요");
        }

        User loginUser = new User(
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().getName(),
                signinRequest.getFcmToken()
        );

        userRepository.save(loginUser);

        return responseWithTokens(loginUser);
    }

    private boolean isNotValidPassword(String plain, String hashed) {
        //String encodedPassword = passwordEncoder.encode(plain);
        return !passwordEncoder.matches(plain, hashed);
    }

    private SigninResponse responseWithTokens(User user) {
        if (StringUtils.isBlank(user.getName())) {
            throw new IllegalArgumentException("user id 없음.");
        }
        return new SigninResponse(
                JwtUtil.createToken(user.getEmail()),
                JwtUtil.refreshToken(user.getEmail()),
                user.getName(),
                user.getId()
        );
    }
}
