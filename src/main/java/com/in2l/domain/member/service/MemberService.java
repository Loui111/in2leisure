package com.in2l.domain.member.service;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.domain.MemberEditor;
import com.in2l.domain.member.domain.MemberEditor.MemberEditorBuilder;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  private String MESSAGE_del = "사용자가 삭제되었습니다.";  //TODO: 이거 글로벌 messages로 전환해야함.
  private String MESSAGE_update = "사용자가 갱신되었습니다.";  //TODO: 이거 글로벌 messages로 전환해야함.
  private String MESSAGE_create = "사용자가 생성되었습니다.";  //TODO: 이거 글로벌 messages로 전환해야함.

  public Member getMemberService(Long memberId) {

    Member member = memberRepository.findById(memberId)
        .orElseThrow(()->new MemberNotFound());
    return member;
  }

  public MemberResponse createMemberService(MemberRequest memberRequest) {

    //TODO: 여기 좀 맘에 안드는데. 이렇게 지저분하게 빌더 써야하나?
    Member member = Member.builder()
        .email(memberRequest.getEmail())
        .password(memberRequest.getPassword())    //TODO: 암호화 필요.
        .memberName(memberRequest.getMemberName())
        .phoneNumber(memberRequest.getPhoneNumber())
        .gender(memberRequest.getGender())
        .birthDay(memberRequest.getBirthDay())    //TODO: Front에서 받아온거 LocalDateTime으로 파싱 필요.
                    //	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    //  LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        .address(memberRequest.getAddress())
        .profileImage(memberRequest.getProfileImage())
        .build();

    Member member1 = memberRepository.save(member);

    MemberResponse memberResponse = MemberResponse.builder()
        .member_id(member1.getMember_id())
        .memberName(member1.getMemberName())
        .email(member1.getEmail())
        .message(MESSAGE_create)
        .build();

    return memberResponse;
  }

  @Transactional
  public MemberResponse updateMember(MemberRequest memberRequest, Long memberId) {
    Member originMember = memberRepository.findById(memberId)
        .orElseThrow( ()-> new MemberNotFound());

    //TODO: 이건 내가 원하는 형태가 아님.
    Member newMember = Member.builder()
        .member_id(memberId)
        .email(memberRequest.getEmail())
        .password(memberRequest.getPassword())
        .memberName(memberRequest.getMemberName())
        .phoneNumber(memberRequest.getPhoneNumber())
        .gender(memberRequest.getGender())
        .birthDay(memberRequest.getBirthDay())
        .address(memberRequest.getAddress())
        .profileImage(memberRequest.getProfileImage())
        .build();

    Member updatedMember = memberRepository.save(newMember);

    MemberResponse memberResponse = MemberResponse.builder()
        .memberName(updatedMember.getMemberName())
        .email(updatedMember.getEmail())
        .member_id(updatedMember.getMember_id())
        .message(MESSAGE_update)
        .build();

//    MemberEditorBuilder memberEditorBuilder = member.edit();
//    //이럼 빌더 자체를 가져옴.
//
//    MemberEditor memberEditor = memberEditorBuilder   //ID 들어가야하지 않아?
//        .email(memberRequest.getEmail())
//        .password(memberRequest.getPassword())
//        .build();

    return memberResponse;
  }

  @Transactional
  public MemberResponse removeMember(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow( ()-> new MemberNotFound());

    MemberResponse memberResponse = MemberResponse.builder()
        .member_id(member.getMember_id())
        .email(member.getEmail())
        .memberName(member.getMemberName())
        .message(MESSAGE_del)
        .build();

    memberRepository.delete(member);

    return memberResponse;
  }
}

