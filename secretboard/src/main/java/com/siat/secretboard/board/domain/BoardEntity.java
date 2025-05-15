package com.siat.secretboard.board.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.siat.secretboard.common.converter.BooleanToIntConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="board")
@Getter
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
    @SequenceGenerator(name = "BOARD_SEQ",sequenceName = "BOARD_SEQ",allocationSize = 1)
    private int boardIdx;
    private String boardName;
    private String boardDescription;
    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
    @Column(name = "is_delete", nullable = false)
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean isDelete;

}
