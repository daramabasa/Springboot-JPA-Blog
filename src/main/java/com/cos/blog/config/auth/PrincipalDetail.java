package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채 로그인을 진행하고 나면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장해준다.
@Getter
public class PrincipalDetail implements UserDetails {

    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는지를 반환, true: 만료 안 됨

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있지 않은지를 반환, true: 잠기지 않음

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호가 만료되었는지를 반환, true: 만료 안 됨

        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화(사용 가능) 상태인지 반환, true: 활성화 상태

        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정이 어떤 권한을 가졌는지 Collection 타입으로 반환

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add((GrantedAuthority) () -> {
            return "ROLE_" + user.getRole(); // ROLE_USER
        });

        return collectors;
    }
}
