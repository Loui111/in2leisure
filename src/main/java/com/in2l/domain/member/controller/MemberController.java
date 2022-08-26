package com.in2l.domain.member.controller;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/v1/member")   //TODO: v1이 여기 들어가는게 맞나?
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/{memberId}")
  public Member getMember(@PathVariable Long memberId) {

    return memberService.getMemberService(memberId);
  }

  @PostMapping("/{memberId}")
  public MemberResponse postMember(@RequestBody @Valid MemberRequest memberRequest) {

    return memberService.createMemberService(memberRequest);
  }

  @PatchMapping("/{memberId}")
  public MemberResponse patchMember(@RequestBody @Valid MemberRequest memberRequest,
      @PathVariable Long memberId) {

    return memberService.updateMember(memberRequest, memberId);
  }

  @DeleteMapping("/{memberId}")
  public MemberResponse deleteMember(@PathVariable Long memberId) {
    return memberService.removeMember(memberId);
  }
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


