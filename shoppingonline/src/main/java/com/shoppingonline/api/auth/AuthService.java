package com.shoppingonline.api.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shoppingonline.base.BaseService;
import com.shoppingonline.common.BeanUtils;
import com.shoppingonline.common.CheckValidate;
import com.shoppingonline.constant.Constant;
import com.shoppingonline.security.AuthorityEntity;
import com.shoppingonline.security.UserEntity;
import com.shoppingonline.utils.MessageUtil;

@Service
public class AuthService implements BaseService<AuthRequest, AuthResponse>{
	
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String LANG = "lang";
	private static final String TYPE = "type";
	private static final String TYPE_ADMIN = "1";
	private static final String TYPE_USER = "2";
	private static final String ACTION_REGISTER = "register";

	private final AuthRepository authRepository;
	
	private MessageUtil messageUtil;
	
	public AuthService(AuthRepository authRepository, MessageUtil messageUtil) {
		this.authRepository = authRepository;
		this.messageUtil = messageUtil;
	}
	
	@Override
	public void save(AuthRequest model ) {
		authRepository.save(buildUserEntity(model));
	}
	
	private UserEntity buildUserEntity(AuthRequest model) {
		UserEntity user = UserEntity.builder()
				.email(model.getEmail())
				.password(model.getPassword())
				.enabled(true)
				.lang(model.getLang())
				.build();
		List<AuthorityEntity> authorities = List.of(TYPE_ADMIN.equals(model.getType()) ? Constant.ROLE_ADMIN : Constant.ROLE_USER).stream()
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
	public boolean prepare(AuthRequest request, AuthResponse response) {
		boolean checkEmail = true;
		boolean checkPassword = true;
		boolean checkType = true;
		boolean check = true;
		
		// email
		String errorCheck = "";
		errorCheck = CheckValidate.isMix(request.getEmail(), true, -1, false, false);
		if (!Constant.AE00000.equals(errorCheck)) {
			response.addErrorItems(new AuthErrorItem(EMAIL, messageUtil.getMessage(errorCheck, Constant.EN)));
			checkEmail = false;
		}
		
		errorCheck = CheckValidate.isEmail(request.getEmail());
		if (checkEmail && !Constant.AE00000.equals(errorCheck)) {
			response.addErrorItems(new AuthErrorItem(EMAIL, messageUtil.getMessage(errorCheck, Constant.EN)));
			checkEmail = false;
		}
		
		if (checkEmail && ACTION_REGISTER.equals(request.getAction()) && existUserByEmail(request.getEmail())) {
			response.addErrorItems(new AuthErrorItem(EMAIL, messageUtil.getMessage(Constant.ERROR_AE00108, Constant.EN)));
			checkEmail = false;
		}
		
		// password
		errorCheck = CheckValidate.isMix(request.getPassword(), true, 50, false, false);
		if (!Constant.AE00000.equals(errorCheck)) {
			response.addErrorItems(new AuthErrorItem(PASSWORD, messageUtil.getMessage(errorCheck, Constant.EN)));
			checkPassword = false;
		}
		
		if (checkPassword && request.getPassword().length() < 8) {
			response.addErrorItems(new AuthErrorItem(PASSWORD, messageUtil.getMessage(Constant.ERROR_AE00107, Constant.EN)));
			checkPassword = false;
		}
		
		check = checkEmail && checkPassword;
		
		if (ACTION_REGISTER.equals(request.getAction())) {
			// type
			errorCheck = CheckValidate.isMix(request.getType(), false, 1, false, false);
			if (!Constant.AE00000.equals(errorCheck)) {
				response.addErrorItems(new AuthErrorItem(TYPE, messageUtil.getMessage(errorCheck, Constant.EN)));
				checkType = false;
			}
			
			if (checkType && BeanUtils.isNotEmpty(request.getType()) && !TYPE_ADMIN.equals(request.getType()) && !TYPE_USER.equals(request.getType())) {
				response.addErrorItems(new AuthErrorItem(TYPE, messageUtil.getMessage(Constant.ERROR_AE00109, Constant.EN)));
				checkType = false;
			}
			check = check && checkType;
	
			// lang
			errorCheck = CheckValidate.isMix(request.getLang(), false, 2, false, false);
			if (!Constant.AE00000.equals(errorCheck)) {
				response.addErrorItems(new AuthErrorItem(LANG, messageUtil.getMessage(errorCheck, Constant.EN)));
				check = false;
			}
		}
		
		return check;
	}

	@Override
	public List<AuthRequest> findAll() {
		return null;
	}
	
	private boolean existUserByEmail(String email) {
		return authRepository.findByEmail(email).isPresent();
	}

}
