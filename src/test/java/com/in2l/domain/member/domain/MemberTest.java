package com.in2l.domain.member.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.in2l.domain.member.repository.MemberRepository;
import com.in2l.domain.member.service.MemberService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private Member member;

  @Autowired
  private MemberService memberService;

  @BeforeEach
  void DBClean(){
    memberRepository.deleteAll();;
  }

//  @Test
//  @DisplayName("Member도메인테스트: Member필드가 전부 있는지 확인"){
//  void MemberExesist_Test(){
//    Long testId = 1L;
//    String testEmail = "test@gmail.com";
//    String testPassword = "password";
//    String testMembername = "고냥인";
//
//    Member member = Member.builder()
//        .member_id(testId)
//        .email(testEmail)
//        .memberName(testMembername)
//        .password(testPassword)
//        .build();
//
//    memberRepository.save(member);
//
//    Long member_id = member.getMember_id();
//
//    //when
//    Member member1 = memberService.getMember(member_id);
//
//    //then
//    Assertions.assertEquals(testId, member1.getMember_id());
//    Assertions.assertEquals(testEmail, member1.getEmail());

//    /**
//     * email            :String
//     * password     :String
//     * membername    :String
//     * phoneNumber  :String
//     * gender         :ENUM
//     * birthDay        :DateTime
//     * address       :String
//     * profileImage :String
//     * registerDate :DateTime
//     * updateDate. :DateTime
//     */
//    //Given
//    //의미 없어 보인다??
//    Member member = Member.builder()
//        .member_id(1L)
//        .email("test@gmail.com")
//        .password("password")
//        .memberName("고냥인")
//        .phoneNumber("010888888")
//        .gender(GenderType.FEMALE)
//        .birthDay(LocalDateTime.now())
//        .address("서울시 송파구")
//        .profileImage("/path")
//        .build();
//
//    Member member1 = memberRepository.save(member);
//
//    Assertions.assertEquals(1L, member1.getMember_id());
//    Assertions.assertEquals("12341234", member1.getEmail());
//
////    Long member1 = member.getMember_id();
////
////    //when
////    Member member1 = memberService.getMember(member_id);
////
////    //then
////    Assertions.assertEquals(testId, member1.getMember_id());
////    Assertions.assertEquals(testEmail, member1.getEmail());
////
//    //expected

  }
