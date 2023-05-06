package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    // http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");

        // 파일 return 기본 경로: src/main/resources/static
        // 리턴명: /home.html
        // 전체 경로: src/main/resources/static/home.html
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        return "/a.gif";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        //jsp는 동적인 파일이라, 정적 파일만 가지는 static 하위에서는 찾지 못 한다(인식하지 못 함)
        return "/test.jsp";
    }

    @GetMapping("/temp/jsp02")
    public String tempJsp02() {
        // prefix: /WEB-INF/views/
        // suffix: .jsp
        // 풀네임: /WEB-INF/views//test.jsp.jsp
        // 따라서 test02로 표기
        return "test02";
    }
}
