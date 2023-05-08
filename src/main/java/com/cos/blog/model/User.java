package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity // User 클래스가 자동으로 MySQL에 테이블 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; //시퀀스, auto_increment

    @Column(nullable = false, length = 30) // NULL 방지, 30자 제한
    private String username; // 아이디

    @Column(nullable = false, length = 100) // NULL 방지, 100자 제한, 암호화된 비밀번호를 넣을 거라 넉넉하게
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // Enum을 쓰는 게 좋음, Admin인지 일반 user인지 manager인지 권한

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;

    // 응용하기 위해 Timestamp updateDate도 넣어보기...
}
