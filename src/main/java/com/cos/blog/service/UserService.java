package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //스프링이 컴포넌트 스캔을 통해 Bean에 등록 (IOC)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional // 트랜잭션으로 처리, 전체가 성공하면 commit, 아닐 시 rollback
    public void userJoin(User user) {
        try {
            String rawPassword = user.getPassword(); // 비밀번호 원문
            String encPassword = encoder.encode(rawPassword); // 해쉬
            user.setPassword(encPassword);

            // 실제로 DB insert 후 아래에서 return
            user.setRole(RoleType.USER);

            userRepository.save(user);
            //return 1;
        } catch (Exception e) {
            System.out.println("UserService: userJoin() : " + e.getMessage());
        }

        //return -1;
    }

    /*@Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료 (정합성 유지 가능)
    public User userLogin(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}
