
package com.soa.login.repository;

import com.soa.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginUserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
