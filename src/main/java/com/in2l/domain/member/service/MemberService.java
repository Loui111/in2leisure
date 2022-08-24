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

  public Member getMember(Long memberId) {

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
        .build();

    return memberResponse;
  }
}

