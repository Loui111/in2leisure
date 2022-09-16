package com.in2l.domain.member.service;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberResponse getMember(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(()->new MemberNotFound());

    return MemberResponse.of(member);
  }

  public MemberResponse postMember(MemberRequest memberRequest) {
//    Member member1 = MemberRequest.of(memberRequest);
    Member savedMember = memberRepository.save(MemberRequest.of(memberRequest));
    return MemberResponse.of(savedMember);
  }

//  @Transactional
  public MemberResponse patchMember(MemberRequest memberRequest, Long memberId) {
    Member originMember = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFound());

//    Member newMember = MemberRequest.of(memberRequest);
    //TODO: 이건 내가 원하는 형태가 아님. 향후에 builder로 update시키는 거 분석 필요.
    Member updatedMember = memberRepository.save(MemberRequest.of(memberRequest));

    return MemberResponse.of(updatedMember);
  }

  //  @Transactional
  public MemberResponse deleteMember(Long memberId) {
    Member member1 = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFound());

    MemberResponse memberResponse = MemberResponse.of(member1);
    memberResponse.setMESSAGE("사용자가 삭제되었습니다.");
    memberRepository.delete(member1);

    return memberResponse;
  }

//    Member tempMemberBuilder = Member.builder()
//        .email(memberRequest.getEmail())
//        .password(memberRequest.getPassword())
//        .memberName(memberRequest.getMemberName())
//        .phoneNumber(memberRequest.getPhoneNumber())
//        .gender(memberRequest.getGender())
//        .birthDay(memberRequest.getBirthDay())
//        .address(memberRequest.getAddress())
//        .profileImage(memberRequest.getProfileImage())
//        .build();


//
//    MemberResponse memberResponse = MemberResponse.builder()
//        .memberName(updatedMember.getMemberName())
//        .email(updatedMember.getEmail())
//        .member_id(updatedMember.getMember_id())
//        .message(MESSAGE_update)
//        .build();

//    MemberEditorBuilder memberEditorBuilder = member.edit();
//    //이럼 빌더 자체를 가져옴.
//
//    MemberEditor memberEditor = memberEditorBuilder   //ID 들어가야하지 않아?
//        .email(memberRequest.getEmail())
//        .password(memberRequest.getPassword())
//        .build();


//
//    MemberResponse memberResponse = MemberResponse.builder()
//        .member_id(member.getMember_id())
//        .email(member.getEmail())
//        .memberName(member.getMemberName())
//        .message(MESSAGE_del)
//        .build();
//
//    memberRepository.delete(member);
//
//    return memberResponse;



//  private Member MemberRequestConverter(MemberRequest memberRequest) {
//    Member member1 = Member.builder()
//        .email(memberRequest.getEmail())
//        .password(memberRequest.getPassword())    //TODO: 암호화 필요.
//        .memberName(memberRequest.getMemberName())
//        .phoneNumber(memberRequest.getPhoneNumber())
//        .gender(memberRequest.getGender())
//        .birthDay(memberRequest.getBirthDay())    //TODO: Front에서 받아온거 LocalDateTime으로 파싱 필요.
//        .address(memberRequest.getAddress())
//        .profileImage(memberRequest.getProfileImage())
//        .build();
//
//    return member1;
//  }

  private MemberResponse MemberResponseConverter(Member member) {
    MemberResponse memberResponse = MemberResponse.builder()
        .member_id(member.getMember_id())
        .email(member.getEmail())
        .memberName(member.getMemberName())
        .build();

    return memberResponse;
  }

}

