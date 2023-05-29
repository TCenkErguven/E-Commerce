package com.eticaret.repository;

import com.eticaret.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findOptionalByEmail(String email);
    Optional<Auth> findByUsernameAndPassword(String username,String password);
}
