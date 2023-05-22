package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    /*@Autowired
    private HttpSession session;*/


    @PostMapping("/auth/joinProc")
    public ResponseDTO<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출");

        userService.userJoin(user);

        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    // 다음 시간에는 스프링 시큐리티를 이용해서 로그인
    // 지금은 전통적인 방식의 로그인
    /*@PostMapping("/api/user/login")
    public ResponseDTO<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("UserApiController: login 호출");

        User principal = userService.userLogin(user); // principal: 접근 주체

        if(principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);

    }*/
}
