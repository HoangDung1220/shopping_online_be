package com.shoppingonline.api.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingonline.security.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);

}
