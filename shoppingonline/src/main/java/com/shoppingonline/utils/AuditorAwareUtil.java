package com.shoppingonline.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.shoppingonline.constant.Constant;
import com.shoppingonline.security.UserDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditorAwareUtil implements AuditorAware<String> {

	private final UserUtil userUtil;
	
	@Override
	public Optional<String> getCurrentAuditor() {
	    Optional<UserDTO> userOpt = userUtil.getUser();
	    
	    if (userOpt.isPresent()) {
	        return Optional.ofNullable(userOpt.get().getEmail()).or(() -> Optional.of(Constant.SYSTEM));
	    }
	    
	    return Optional.of(Constant.SYSTEM);	}
}
