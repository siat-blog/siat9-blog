package com.siat.secretboard.post.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siat.secretboard.post.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    @Query("SELECT p FROM Post p WHERE p.isDelete = false AND p.title LIKE %:title%")
    List<PostEntity> searchByTitle(@Param("title") String title);
}
