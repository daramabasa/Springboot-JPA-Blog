package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Bean 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // IOC 관리
@EnableWebSecurity(debug = true) // 시큐리티 필터 등록: 활성화된 스프링 시큐리티 설정을 해당 파일에서 진행
@EnableMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인 시 password가 어떻게 해쉬가 되어 회원가입이 되었는지 알고 같게 암호화해서 DB의 해쉬랑 비교
    /*@Bean
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    .csrf().disable()// csrf 토큰 비활성화 (테스트 할 때에는 걸어두는 게 좋음)
                .httpBasic().disable()
                .cors().disable()
                .authorizeRequests()
                    .requestMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 오는 로그인 요청을 가로챈다 (대신 로그인)
                    .defaultSuccessUrl("/"); // 성공하면 메인 페이지로 이동
                    //.failureUrl("/fail"); // 실패하면 fail 페이지로 이동
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/WEB-INF/views/user/**", "/WEB-INF/views/**", "/error");
    }
}
