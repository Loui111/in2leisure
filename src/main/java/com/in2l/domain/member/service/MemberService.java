package com.in2l.domain.member.service;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.domain.MemberEditor;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberResponse getMember(Long id) {
    Member member = memberRepository.findByIdWithDeleteFlagFalse(id)
        .orElseThrow(() -> new MemberNotFound());

    return MemberResponse.of(member);
  }

  public MemberResponse postMember(MemberRequest memberRequest) {
    Member savedMember = memberRepository.save(Member.of(memberRequest));
    return MemberResponse.of(savedMember);
  }

//  @Transactional
  public MemberResponse patchMember(MemberRequest memberRequest, Long id) {
    Member originMember = memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFound());

    MemberEditor.of(originMember.toEditor(), originMember);
    originMember.edit(MemberEdit.of(memberRequest));
    memberRepository.save(originMember);

    MemberResponse memberResponse = MemberResponse.of(originMember);
    memberResponse.setMESSAGE("사용자의 정보가 갱신되었습니다.");

    return memberResponse;

    //@@@@@@@@@@ 위에걸 풀어다 쓴게 아래와 같음...(솔까 명확히 이해는 몬하겠음..)
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
  }

  //  @Transactional
  public MemberResponse deleteMember(Long id) {
    Member member1 = memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFound());

    //원래는 memberRequest가 같이 와야 하는데 따로 주진 않아서 여기서 걍 알아서 만듦.
    MemberRequest memberRequest = MemberRequest.of(member1);
    memberRequest.setDeleteFlag(Boolean.TRUE);      //TODO: 이딴건 하면 안됨.

    MemberEditor.of(member1.toEditor(), member1);
    member1.edit(MemberEdit.of(memberRequest));
    memberRepository.save(member1);

    MemberResponse memberResponse = MemberResponse.of(member1);
    memberResponse.setMESSAGE("사용자가 삭제되었습니다.");

    return memberResponse;
  }
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

  public List<Member> getMembers() {
    return  memberRepository.findAll();
  }

  public Optional<List<Member>> getMembersWithoutDelete() {
    Optional<List<Member>> members = memberRepository.findAllWithoutDelete();
    return members;
  }
}

