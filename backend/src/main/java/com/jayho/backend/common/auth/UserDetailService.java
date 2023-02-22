package com.jayho.backend.common.auth;

import com.jayho.backend.api.service.UserService;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
    		User user = userRepository.findByUserEmail(userEmail).orElse(null);
    		if(user != null) {
    			UserDetails userDetails = new UserDetails(user);
    			return userDetails;
    		}
    		return null;
    }
}
