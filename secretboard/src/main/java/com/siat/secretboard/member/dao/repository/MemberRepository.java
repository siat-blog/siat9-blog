package com.siat.secretboard.member.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siat.secretboard.member.domain.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
}
