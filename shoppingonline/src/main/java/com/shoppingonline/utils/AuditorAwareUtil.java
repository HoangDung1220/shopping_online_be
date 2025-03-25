package com.shoppingonline.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.shoppingonline.constant.Constant;

@Component
public class AuditorAwareUtil implements AuditorAware<String> {

	private UserUtil userUtil;

	public AuditorAwareUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return userUtil.getUser().map(user -> Optional.ofNullable(user.getEmail()).orElse(Constant.BLANK));
	}
}
