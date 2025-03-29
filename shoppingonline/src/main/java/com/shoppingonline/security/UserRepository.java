package com.shoppingonline.security;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserEntity.class, idClass = Long.class)
public interface UserRepository {
	
	Optional<UserEntity> findByEmail(String email);
}
