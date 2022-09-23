package com.in2l.domain.member.service;

import static org.junit.jupiter.api.Assertions.*;

import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.dto.response.MemberResponse;
import com.in2l.domain.member.repository.MemberRepository;
import com.mysema.commons.lang.Assert;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  MemberService memberService;

  /**
   * @email            :String
   * @password     :String
   * @name    :String
   * phoneNumber  :String? Int?
   * gender         :ENUM
   * birthDay        :DateTime
   * address       :String
   * profileImage :String
   */

  String testEmail = "test@gmail.com";
  String testPassword = "Password123!@#";
  String testName = "고냥인";
  String testPhoneNumber = "0108811234";
  GenderType testGender = GenderType.MALE;
  LocalDateTime testBirthDay = LocalDateTime.now();
  String testAddress = "송파구";
  String testProfileImage = "/path/image.jpg";

//  @Test
//  @DisplayName("멤버 1명 조회")    //TODO: 이건 솔까 뭔가 이상한 테스트케이스임.
//  void Test_1MemberSearch(){
//    //given
//    Member member = Member.builder()
//        .email(testEmail)
//        .name(testname)
//        .password(testPassword)
//        .build();
//
//    Member savedMember = memberRepository.save(member);
//
//    //when
//    MemberResponse memberResponse = memberService.getMember(savedMember.getId());
//
//    //then
//    Assertions.assertEquals(memberResponse.getId(), savedMember.getId());
//  }
//
//  @Test
//  @DisplayName("맴버 1명 생성")
//  void Test_1MemberCreate(){
//    //MemberRequest를 통해 Front에서 사용자가 들어오고, valid체크되고,
//
//    //Given
//    MemberRequest memberRequest = MemberRequest.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .name(testname)
//        .gender(testGender)
//        .birthDay(testBirthDay)
//        .address(testAddress)
//        .profileImage(testProfileImage)
//        .build();
//
//    //when
//    MemberResponse member1 = memberService.postMember(memberRequest);
//
//    //then
//    Assertions.assertNotNull(member1);
//    Assertions.assertEquals(testname, member1.getname());
//    Assertions.assertEquals(testEmail, member1.getEmail());
//  }
//
//  @Test
//  @DisplayName("맴버 생성시 비번오류.")
//  void Test_1MemberCreate_error_password(){
//    //MemberRequest를 통해 Front에서 사용자가 들어오고, valid체크되고,
//
//    //Given
//    MemberRequest memberRequest = MemberRequest.builder()
//        .email(testEmail)
//        .password("8901")
//        .name(testname)
//        .gender(testGender)
//        .birthDay(testBirthDay)
//        .address(testAddress)
//        .profileImage(testProfileImage)
//        .build();
//
//    //when
//    MemberResponse member1 = this.memberService.postMember(memberRequest);
//
//    //then
//    Assertions.assertNotNull(member1);
//    Assertions.assertEquals(testname, member1.getname());
//    Assertions.assertEquals(testEmail, member1.getEmail());
//  }
}

