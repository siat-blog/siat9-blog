package com.siat.secretboard.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siat.secretboard.board.domain.BoardType;
import com.siat.secretboard.member.dao.repository.MemberRepository;
import com.siat.secretboard.member.domain.MemberEntity;
import com.siat.secretboard.member.dto.MemberRequestDTO;
import com.siat.secretboard.member.dto.MemberResponseDTO;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository repository;
    

    @Override
    public MemberResponseDTO signupService(MemberRequestDTO params) {
        // repository
        /*현재 아이디가 존재하는지?*/
        Optional<MemberEntity> id = repository.findById(params.getMemberId());
        if (id.isPresent()) { // 존재하면, 이미 사용하고 있으므로, 생성불가.
            // 만들수 없다는 에러를 발생시키면된다.
        }
        // entity 생성시키고 save 적용한다.
        // 충분히 잘 검증된 데이터라고 가정한다. -> 데이터 검증은 어디서 진행할까? ctrl에서 검증하거나 서비스에서 검증한다.
        MemberEntity entity  = MemberEntity.builder()
                    .memberId(params.getMemberId())
                    .memberPassword(params.getMemberPassword())
                    .memberNickname(params.getMemberNickname())
                    .boardIdx(BoardType.toNumber(params.getBoardType()))
                    .build();

        // save 적용.
        repository.save(entity);

        // DTO 담아서 적용한다. -> 우선 적용해줄 필요는 없지만 일단 체크해서 만들어둠.
        return  MemberResponseDTO.builder()
                                    .memberId(entity.getMemberId())
                                    .memberPassword(entity.getMemberPassword())
                                    .memberNickname(entity.getMemberNickname())
                                    .boardType(BoardType.toString(entity.getBoardIdx()))
                                    .build(); 
    }

    @Override
    public MemberResponseDTO loginService(MemberRequestDTO params) {
        // repository
        /*현재 아이디가 존재하는지?*/
        Optional<MemberEntity> member = repository.findByMemberIdAndMemberPassword(params.getMemberId(), params.getMemberPassword());
        if (member.isPresent()) { // 존재하면, 이미 사용하고 있으므로, 생성불가.
            // 만들수 없다는 에러를 발생시키면된다.
        }
        return  MemberResponseDTO.builder()
                                    .memberId(member.get().getMemberId())
                                    .memberPassword(member.get().getMemberPassword())
                                    .memberNickname(member.get().getMemberNickname())
                                    .boardType(BoardType.toString(member.get().getBoardIdx()))
                                    .build(); 
    }
}
