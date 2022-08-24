package com.in2l.domain.member.controller;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/member/{memberId}")
  public Member getMember(@PathVariable Long memberId){

    Member member = memberService.getMember(memberId);

    return member;
  }

  @PostMapping("/member")
  public MemberResponse createMember(@RequestBody @Valid MemberRequest memberRequest){  //requestDTO가 필요한가??

    MemberResponse memberResponse = memberService.createMemberService(memberRequest);

    return memberResponse;

  }

//  @PostMapping("/posts")
//  public void post(@RequestBody @Valid PostCreate request){
//    request.validate();   //
//
////    if (request.getTitle().contains("바보")) {    //당연한거지만, 이렇게 데이터를 꺼내는 행위는 지양해야함.
////      throw new InvalidRequest();
////    }
//
//    postService.write(request);
////    return Map.of();
//  }

}

