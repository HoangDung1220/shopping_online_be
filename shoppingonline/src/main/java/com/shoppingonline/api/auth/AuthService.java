package com.shoppingonline.api.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shoppingonline.base.BaseService;
import com.shoppingonline.constant.Constant;
import com.shoppingonline.security.AuthorityEntity;
import com.shoppingonline.security.UserEntity;

@Service
public class AuthService implements BaseService<AuthRequest, String>{
	
	private final AuthRepository authRepository;
	
	public AuthService(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}
	
	@Override
	public void save(AuthRequest model) {
		authRepository.save(buildUserEntity(model));
	}
	
	private UserEntity buildUserEntity(AuthRequest model) {
		UserEntity user = UserEntity.builder()
				.email(model.getEmail())
				.password(model.getPassword())
				.enabled(true)
				.lang(model.getLang())
				.build();
		List<AuthorityEntity> authorities = List.of(Constant.ROLE_ADMIN).stream()
												.map(role -> buildAuthority(role, user))
												.collect(Collectors.toList());
		user.setAuthorities(authorities);
		return user;	
	}
	
    private AuthorityEntity buildAuthority(String role, UserEntity user) {
        return AuthorityEntity.builder()
                .authority(role)
                .user(user)
                .build();
    }

	@Override
	public boolean prepare(AuthRequest model) {
		return false;
	}

	@Override
	public List<AuthRequest> findAll() {
		return null;
	}

}
