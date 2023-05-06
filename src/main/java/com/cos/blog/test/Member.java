package com.cos.blog.test;

import lombok.*;

//@Getter
//@Setter
//@RequiredArgsConstructor //final 붙은 변수를 대상으로 생성자 자동 생성
@Data // getter, setter 자동 생성
// @AllArgsConstructor //전체 생성자 자동 생성
@NoArgsConstructor //빈 생성자
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;
    // final : 불변성 유지

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
