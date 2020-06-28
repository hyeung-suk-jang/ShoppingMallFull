package com.example.parayo.domain.auth;

import com.example.parayo.common.ParayoException;
import com.example.parayo.domain.user.User;
import com.example.parayo.domain.user.UserRepository;
import com.example.parayo.dto.SignupRequest;
import com.example.parayo.global.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {
    public static final String EMAIL_ALREADY_USED_ERROR = "이미 사용 중인 이메일입니다.";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupRequest signupRequest) {
        validateRequest(signupRequest);
        checkDuplicateEmail(signupRequest);
        return registerUser(signupRequest);
    }

    private void validateRequest(SignupRequest signupRequest) {
        StringUtils.validateEmail(signupRequest.getEmail());
        StringUtils.validateName(signupRequest.getName());
        StringUtils.validatePassword(signupRequest.getPassword());
    }

    private void checkDuplicateEmail(final SignupRequest signupRequest) {
        userRepository.findByEmail(signupRequest.getEmail()).ifPresent(e -> {
            throw new ParayoException(EMAIL_ALREADY_USED_ERROR);
        });
    }

    private User registerUser(SignupRequest signupRequest) {
        //String hashedPassword = BCrypt.hashpw(signupRequest.getPassword(), BCrypt.gensalt());
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        String fcmToken = signupRequest.getFcmToken();

        User user = new User(signupRequest.getEmail(), hashedPassword
                , signupRequest.getName(), fcmToken != null ? fcmToken : null);
        return userRepository.save(user);
    }


}
