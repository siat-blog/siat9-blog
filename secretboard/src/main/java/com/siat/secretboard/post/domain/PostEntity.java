package com.siat.secretboard.post.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import com.siat.secretboard.common.converter.BooleanToIntConverter;
import com.siat.secretboard.member.domain.MemberEntity;

@Entity
@Table(name = "post")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
    @SequenceGenerator(name="POST_SEQ",sequenceName = "POST_SEQ",allocationSize = 1)
    @Column(name = "post_idx")
    private Long id;

    // @Column(name = "member_idx", nullable = false)
    // private Long memberId;

    @Column(name = "post_author", length = 100, nullable = false)
    private String author;

    @Column(name = "post_title", length = 250, nullable = false)
    private String title;

    @Column(name = "post_content", columnDefinition = "NCLOB")
    private String content;
    @Builder.Default
    @Column(name = "hit", nullable = false)
    private Integer hit =0;;

    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Column(name = "is_delete", nullable = false)
    @Convert(converter = BooleanToIntConverter.class)
    @Builder.Default
    private Boolean isDelete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx",nullable = true)
    private MemberEntity member;

    @PrePersist
    protected void onCreate() {
        this.regDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}