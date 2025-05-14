package com.siat.secretboard.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MemberEntity {
    @Id
    private String member_Idx;
}
