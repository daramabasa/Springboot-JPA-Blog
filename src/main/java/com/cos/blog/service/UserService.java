package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //스프링이 컴포넌트 스캔을 통해 Bean에 등록 (IOC)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional // 트랜잭션으로 처리, 전체가 성공하면 commit, 아닐 시 rollback
    public void userJoin(User user) {
        try {
            userRepository.save(user);
            //return 1;
        } catch (Exception e) {
            System.out.println("UserService: userJoin() : " + e.getMessage());
        }

        //return -1;
    }

    @Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료 (정합성 유지 가능)
    public User userLogin(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
