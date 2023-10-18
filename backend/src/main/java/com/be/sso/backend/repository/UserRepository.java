package com.be.sso.backend.repository;

import com.be.sso.backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    @Query(value="select * from users where email=:email and password=:password", nativeQuery=true)
    Optional<Users> findByEmailAndPassword(String email, String password);
}
