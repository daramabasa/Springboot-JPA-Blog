package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    @Column(columnDefinition = "LONGTEXT")
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨

    //@ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, One = User, Board를 select 하면 User도 바로 들고 옮
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트 저장 불가, 자바는 저장 가능 / FK

    // 무조건 들고오라는 건 EAGER, 아니면 LAZY  (default)
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 가 있으면 FK가 아니고 DB에 컬럼을 만들지 말라는 의미, 연관관계의 주인이 아님을 의미
    // @JoinColumn(name = "replyId") // FK가 필요하지 않아서 필요 없는 구문이 됨
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}

