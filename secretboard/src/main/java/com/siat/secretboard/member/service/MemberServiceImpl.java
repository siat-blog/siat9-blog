// package com.siat.secretboard.member.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.siat.secretboard.board.domain.BoardType;
// import com.siat.secretboard.member.dao.repository.MemberRepository;
// import com.siat.secretboard.member.domain.MemberEntity;
// import com.siat.secretboard.member.dto.MemberRequestDTO;
// import com.siat.secretboard.member.dto.MemberResponseDTO;

// @Service
// public class MemberServiceImpl implements MemberService{
//     @Autowired
//     private MemberRepository repository;
    

//     @Override
//     public MemberResponseDTO signup(MemberRequestDTO params) {
//         // repository
//         /*현재 아이디가 존재하는지?*/
//         Optional<MemberEntity> member = repository.findByMemberId(params.getMemberId());
//         if (member.isPresent()) { // 존재하면, 이미 사용하고 있으므로, 생성불가.
//             // 만들수 없다는 에러를 발생시키면된다.
            
//         }
//         // entity 생성시키고 save 적용한다.
//         // 충분히 잘 검증된 데이터라고 가정한다. -> 데이터 검증은 어디서 진행할까? ctrl에서 검증하거나 서비스에서 검증한다.
//         MemberEntity entity  = MemberEntity.builder()
//                     .memberId(params.getMemberId()) // 문자열로 타입 수정 필요.
//                     .memberPassword(params.getMemberPassword())
//                     .memberNickname(params.getMemberNickname())
//                     .boardIdx(BoardType.toNumber(params.getBoardType()))
//                     .build();

//         // save 적용.
//         repository.save(entity);

//         // DTO 담아서 적용한다. -> 우선 적용해줄 필요는 없지만 일단 체크해서 만들어둠.
//         return  MemberResponseDTO.builder()
//                                     .memberId(entity.getMemberId()) // 문자열로 타입 수정 필요.
//                                     .memberPassword(entity.getMemberPassword())
//                                     .memberNickname(entity.getMemberNickname())
//                                     .boardType(BoardType.toString(entity.getBoardIdx()))
//                                     .build(); 
//     }

//     @Override
//     public MemberResponseDTO login(MemberRequestDTO params) {
//         // repository
//         /*현재 아이디가 존재하는지?*/
//         Optional<MemberEntity> member = repository.findByMemberIdAndMemberPassword(params.getMemberId(), params.getMemberPassword());
//         if (member.isPresent()) { // 존재하면, 이미 사용하고 있으므로, 생성불가.
//             // 만들수 없다는 에러를 발생시키면된다.
//         }
//         return  MemberResponseDTO.builder()
//                                     .memberId(member.get().getMemberId())
//                                     .memberPassword(member.get().getMemberPassword())
//                                     .memberNickname(member.get().getMemberNickname())
//                                     .boardType(BoardType.toString(member.get().getBoardIdx()))
//                                     .build(); 
//     }
// }


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