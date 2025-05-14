package com.siat.secretboard.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PostEntity {
    @Id
    private int post_Idx;
}
