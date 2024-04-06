package com.bhanuka.tms.services.jwt.auth;

import com.bhanuka.tms.dto.SignupRequest;
import com.bhanuka.tms.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
