
package com.soa.login.service;

import com.soa.login.model.User;
import com.soa.login.repository.LoginUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginAuthService {

    private final LoginUserRepository loginUserRepository;

    public LoginAuthService(
            LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    public String authenticate(String username, String password) {

        User user = loginUserRepository
                .findByUsername(username)
                .orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return "Authentication Successful";
        }

        return "Authentication Failed";
    }
}
