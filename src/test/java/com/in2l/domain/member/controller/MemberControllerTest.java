package com.in2l.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.member.repository.MemberRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

  @Autowired
  private ObjectMapper objectMapper;    //json용

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MemberRepository memberRepository;

  @BeforeEach
  void DBClean(){
    memberRepository.deleteAll();;
  }

  Long testId = 8898L;
  String testEmail = "test@gmail.com";
  String testPassword = "Password123!@#";
  String testMembername = "고냥인";
  String testPhoneNumber = "0108811234";
  GenderType testGender = GenderType.MALE;
  LocalDateTime testBirthDay = LocalDateTime.now();
  String testAddress = "송파구";
  String testProfileImage = "/path/image.jpg";

  @Test
  @DisplayName("1명 get")
  void Test_get1Member() throws Exception{

    //Given
    Member member = Member.builder()
        .member_id(testId)
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .build();

    String json = objectMapper.writeValueAsString(member);

    memberRepository.save(member);

    //expected
    mockMvc.perform(get("/v1/member/1")
    .contentType(MediaType.APPLICATION_JSON)
    .contentType(json))
        .andExpect(status().isOk())
      .andDo(print());

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
  }

  @Test
  @DisplayName("1명 삭제 확인.")
  void Test_delete1Member() throws Exception{ //TODO: beforeeach 가 왜 안먹지?? DB초기화가 안됨.

    //Given
    Member member = Member.builder()
        .member_id(testId)
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .build();

    Member member1 = memberRepository.save(member);

    //when
    mockMvc.perform(delete("/v1/member/{memberId}", member1.getMember_id())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());

    //then
    //TODO: 에러메세지 "message":"사용자가 삭제되었습니다." 가 잘 출력되는지 확인 테케가 있어야함.
    Assertions.assertEquals(member1.getMember_id(), 1L);

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
  }

  @Test
  @DisplayName("1명 업데이트 확인")
  void Test_patch1Member() throws Exception {
    //Given
    Member member = Member.builder()
        .member_id(testId)
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .build();

    Member member1 = memberRepository.save(member);

    //when
    MemberRequest memberRequest = MemberRequest.builder()
        .email("updatedEmail@gmail.com")
        .password(testPassword)
        .memberName("updated고냥인")
        .address("updated송파구")
        .profileImage("updated/path/")
        .birthDay(LocalDateTime.now())
        .gender(GenderType.MALE)
        .phoneNumber("updated010812345")
        .build();

    mockMvc.perform(patch("/v1/member/{memberId}", member1.getMember_id())
      .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(memberRequest)))
        .andExpect(status().isOk());

//        objectMapper.writeValueAsString(data);

//        .content(objectMapper.toJson(MemberRequest))
//        .content(objectMapper.writeValueAsString(MemberRequest)))
//        .andExpect(HttpStatus.OK);

    //then




  }



  }

//
//
//  @Test
//  void getUser() throws Exception {
//    //given
////    User user = User.builder()
////        .user_id(1L)
////        .userName("노여름")
////        .address("장미아파트")
////        .birthDay(LocalDateTime.now())
////        .email("abc@naver.com")
////        .gender(GenderType.MALE)
////        .phoneName("01012341234")
////        .password("8901")
////        .registerDate(LocalDateTime.now())
////        .updateDate(LocalDateTime.now())
////        .build();
//
//    User user = User.builder()
//        .user_id(1L)
//        .email("emails")
//        .build();
//
//    //when
//    User user1 = userRepository.save(user);
//
//    //then
////    mockMvc.perform(MockHttpServletRequestBuilder  );
//
////    mockMvc.perform(get, "/user/{userId}")
////    mockMvc.perform(get)
//    mockMvc.perform(get("/user/{userId}", user1.getUser_id())
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
////        .andExpect(jsonPath("$.id").value(user1.getUser_id()))
////        .andExpect(jsonPath("$.title").value("1234567789")) //만약 title을 10글자만 받아달라는 희안한 요구일때.
////        .andExpect(jsonPath("$.content").value("bar"))
////        .andExpect(jsonPath("$.content").value(post.getContent()))
//        .andDo(print());
//
//  }
//}