package com.siat.secretboard.post.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private Long id;



    @Column(name = "created_date")
    private String title;
    @Column(name = "created_date")
    private String content;
    @Column(name = "created_date")
    private String author;
    
    @Column(name = "reg_date")
    private LocalDateTime regDate; // 자동으로 생성을 어디서 할건지?

    // 생성자
    // public Post() {
    //     this.createdDate = LocalDateTime.now();
    // }

    // public Post(String title, String content, String author) {
    //     this.title = title;
    //     this.content = content;
    //     this.author = author;
    //     this.createdDate = LocalDateTime.now();
    // }

    
}
