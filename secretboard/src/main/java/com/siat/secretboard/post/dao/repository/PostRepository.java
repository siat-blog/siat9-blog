package com.siat.secretboard.post.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siat.secretboard.post.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
