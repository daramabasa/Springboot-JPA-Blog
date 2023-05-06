package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청하면 응답(Data)을 해줌
//데이터가 아닌 HTML 파일을 응답해주는 건 @Controller
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";


    @GetMapping("/http/lombok")
    public String lombokTest() {
        //Member m1 = new Member(1, "test", "234129", "@naver.com");
        Member m1 = Member.builder().username("test").password("1124").email("@naver.com").build();

        System.out.println(TAG + "getter: " + m1.getUsername());
        m1.setUsername("cos");
        System.out.println(TAG + "getter: " + m1.getUsername());

        return "lombok test 완료";
    }

    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member m) {
        //id=1&username=ssar&password=1234&email=ssar@nate.com
        return "get요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // 인터넷 브라우저 요청은 get 요청만 가능하다.
    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) { //MessageConverter (스프링부트가 파싱)
        // html의 form 태그를 이용해 요청
        return "post요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
        // return "post요청: " + text;
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete요청";
    }
}
