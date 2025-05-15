package com.siat.secretboard.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siat.secretboard.board.domain.BoardType;
import com.siat.secretboard.common.exception.CustomException;
import com.siat.secretboard.member.dao.repository.MemberRepository;
import com.siat.secretboard.member.domain.MemberEntity;
import com.siat.secretboard.member.dto.MemberRequestDTO;
import com.siat.secretboard.member.dto.MemberResponseDTO;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository repository;

    @Override
    public MemberResponseDTO signup(MemberRequestDTO params) {
        // 아이디 중복 체크
        if (repository.findByMemberId(params.getMemberId()).isPresent()) {
            throw new CustomException("이미 사용 중인 아이디입니다.");
        }

        // // 데이터 검증 (예: 비밀번호 최소 길이 체크)
        // if (params.getMemberPassword().length() < 8) {
        //     throw new CustomException("비밀번호는 최소 8자 이상이어야 합니다.");
        // }

        // MemberEntity 생성 및 저장
        MemberEntity entity = MemberEntity.builder()
                .memberId(params.getMemberId())
                .memberPassword(params.getMemberPassword())
                .memberNickname(params.getMemberNickname())
                .boardIdx(BoardType.toNumber(params.getBoardType()))
                .build();

        repository.save(entity);

        // MemberResponseDTO 반환
        return MemberResponseDTO.builder()
                .memberId(entity.getMemberId())
                .memberPassword(entity.getMemberPassword())
                .memberNickname(entity.getMemberNickname())
                .boardType(BoardType.toString(entity.getBoardIdx()))
                .build();
    }

    @Override
    public MemberResponseDTO login(MemberRequestDTO params) {
        // 아이디와 비밀번호로 사용자 조회
        Optional<MemberEntity> member = repository.findByMemberIdAndMemberPassword(params.getMemberId(), params.getMemberPassword());
        if (member.isEmpty()) {
            throw new CustomException("아이디 또는 비밀번호가 잘못되었습니다.");
        }

        // MemberResponseDTO 반환
        return MemberResponseDTO.builder()
                .memberId(member.get().getMemberId())
                .memberPassword(member.get().getMemberPassword())
                .memberNickname(member.get().getMemberNickname())
                .boardType(BoardType.toString(member.get().getBoardIdx()))
                .build();
    }
}