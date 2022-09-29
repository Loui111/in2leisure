package com.in2l.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberEdit;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Slf4j
@RequiredArgsConstructor
@ResponseBody
public class MemberService {

  private final MemberRepository memberRepository;

  public Object getMember(Long id) {
    Member member = memberRepository.findByIdWithDeleteFlagFalse(id)
        .orElseThrow(() -> new MemberNotFound());
    MemberResponse memberResponse = MemberResponse.of(member);

    log.info("backend: {}, {}", Thread.currentThread().getStackTrace()[1].getMethodName(),
        memberResponse.toString());

    return memberResponse;
  }

  public Object postMember(MemberRequest memberRequest) throws Exception {
    try {   //TODO: save할때 try catch 필요함? 굳이??
      Member savedMember = memberRepository.save(Member.of(memberRequest));

      log.info("backend: {}, {}", Thread.currentThread().getStackTrace()[1].getMethodName(),
          savedMember.toString());
//
//      log.info("backend: {}, {}", this.getClass().getName(),
//          savedMember.toString());


      return MemberResponse.of(savedMember);
    } catch (Exception e) {
      throw new Exception();
    }
  }

  public MemberResponse patchMember(MemberRequest memberRequest, Long id) {
    Member originMember = memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFound());

//    originMember.edit(MemberEdit.of(memberRequest));
//    MemberResponse memberResponse = MemberResponse.of(memberRepository.save(originMember));
/*
    if (memberRequest.isDeleteFlag() == true) {
      memberResponse.setMessage("Member: " + memberResponse.getEmail() + " 의 delete가 처리되었습니다.");
    } else {
      memberResponse.setMessage("사용자의 정보가 갱신되었습니다.");
    }*/

    return null;
  }

  public boolean deleteMember(Long id) throws Exception {
    try {
      memberRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new Exception();
    }
  }

  //@@@@@@@@@@ 위에걸 풀어다 쓴게 아래와 같음...
//    MemberEditorBuilder memberEditorBuilder = originMember.toEditor();
//    MemberEditor memberEditor = memberEditorBuilder
//        .email(originMember.getEmail())
//        .password(originMember.getPassword())
//        .name(originMember.getName())
//        .phoneNumber(originMember.getPhoneNumber())
//        .gender(originMember.getGender())
//        .birthDay(originMember.getBirthDay())
//        .address(originMember.getAddress())
//        .profileImage(originMember.getProfileImage())
//        .deleteFlag(originMember.getDeleteFlag())
//        .build();
//
//    MemberEdit memberEdit = MemberEdit.builder()
//        .email(memberRequest.getEmail())
//        .password(memberRequest.getPassword())
//        .name(memberRequest.getName())
//        .phoneNumber(memberRequest.getPhoneNumber())
//        .gender(memberRequest.getGender())
//        .birthDay(memberRequest.getBirthDay())
//        .address(memberRequest.getAddress())
//        .profileImage(memberRequest.getProfileImage())
//        .deleteFlag(memberRequest.getDeleteFlag())
//        .build();
//
//    originMember.edit(memberEdit);
//
//    memberRepository.save(originMember);
//
//    MemberResponse memberResponse = MemberResponse.of(originMember);
//    memberResponse.setMESSAGE("사용자의 정보가 갱신되었습니다.");
//
//    return memberResponse;
  //@@@@@@@@@@

  //객체 안보내고 id만 보내서 patch 하는 로직. 필요 없지만 혹시 몰라 냅둠.
//  public MemberResponse updateDeleteFlagMember(Long id) {
//    Member member1 = memberRepository.findById(id)
//        .orElseThrow(() -> new MemberNotFound());
//
//    //원래는 memberRequest가 같이 와야 하는데 따로 주진 않아서 여기서 걍 알아서 만듦.
//    MemberRequest memberRequest = MemberRequest.of(member1);
//    memberRequest.setDeleteFlag(Boolean.TRUE);      //TODO: 이딴건 하면 안됨.
//
//    MemberEditor.of(member1.toEditor(), member1);
//    member1.edit(MemberEdit.of(memberRequest));
//    memberRepository.save(member1);
//
//    MemberResponse memberResponse = MemberResponse.of(member1);
//    memberResponse.setMESSAGE("사용자가 삭제되었습니다.");
//
//    return memberResponse;
//  }
//

//  MemberEditorBuilder memberEditorBuilder = member1.toEditor();
//  //import com.in2l.domain.member.domain.MemberEditor.MemberEditorBuilder; 요게 있어야 한다?
//
//  MemberEditor memberEditor = memberEditorBuilder //.builder가 없다.
//      .email(member1.getEmail())
//      .password(member1.getPassword())
//      .name(member1.getName())
//      .phoneNumber(member1.getPhoneNumber())
//      .gender(member1.getGender())
//      .birthDay(member1.getBirthDay())
//      .address(member1.getAddress())
//      .profileImage(member1.getProfileImage())
//      .deleteFlag(member1.getDeleteFlag())
//      .build();
//
//  MemberEdit memberEdit = MemberEdit.builder()
//      .email(memberEditor.getEmail())
//      .password(memberEditor.getPassword())
//      .name(memberEditor.getName())
//      .phoneNumber(memberEditor.getPhoneNumber())
//      .gender(memberEditor.getGender())
//      .birthDay(memberEditor.getBirthDay())
//      .address(memberEditor.getAddress())
//      .profileImage(memberEditor.getProfileImage())
//      .deleteFlag(Boolean.TRUE)
//      .build();
//
//    member1.edit(memberEdit);
//
//    memberRepository.save(member1);
//
//  MemberResponse memberResponse = MemberResponse.of(member1);
//    memberResponse.setMESSAGE("사용자가 삭제되었습니다.");
//
//    return memberResponse;

  public List<Member> getAllMembers() {
    return memberRepository.findAll();
  }

  public Optional<List<Member>> getMembersWithoutDelete() {
    return memberRepository.findAllWithoutDelete();
  }

  public Optional<List<Member>> getMembersWithDeleteFalseOnly() {
    return memberRepository.findAllWithDeleteFlagOnly();
  }

  public String getMethodName(MemberService memberService) {
    return Thread.currentThread().getStackTrace()[1].getMethodName();
  }

  private String getLoggingResponse(Object o) throws JsonProcessingException {
    return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(o);
  }
}

