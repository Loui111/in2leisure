package com.in2l.controller;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.service.MemberService;
import java.util.List;
import java.util.Optional;
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
@RequestMapping(value = "${apiVersion}/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/{id}")
  public Object getMember(@PathVariable Long id){
    return memberService.getMember(id);
  }

  @PostMapping("")
  public Object postMember(@RequestBody @Valid MemberRequest memberRequest) throws Exception{
    memberRequest.NameValidation();
    return memberService.postMember(memberRequest);
  }

  @PatchMapping("/{id}")      //delete도 patch 로 퉁친다.
  public Object patchMember(@RequestBody @Valid MemberRequest memberRequest, @PathVariable Long id){
    memberRequest.NameValidation();
    return memberService.patchMember(memberRequest, id);
  }

  @DeleteMapping("/{id}")     //진짜 'delete'는 안쓸 예정.
  public boolean deleteMember(@PathVariable Long id) throws Exception {
    return memberService.deleteMember(id);
  }

  @GetMapping("")   //전체조회는 admin 에서나 쓸것. 그래도 겸사 겸사 만들어둠.
  public Optional<List<Member>> getMembersWithDeleteFalseOnly() {
    return memberService.getMembersWithDeleteFalseOnly();
  }

  public List<Member> getAllMembers() {
    return memberService.getAllMembers();
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


