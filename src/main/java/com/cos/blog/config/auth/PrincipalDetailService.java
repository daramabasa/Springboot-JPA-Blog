package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로챌 때
        // password는 부분 처리 알아서, username은 DB에 있는지 확인(해당 함수)

        User princiapl = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException(username + "해당 사용자를 찾을 수 없습니다.");
                });

        return new PrincipalDetail(princiapl);
        // 시큐리티 세션에 유저 정보 저장
    }
}
