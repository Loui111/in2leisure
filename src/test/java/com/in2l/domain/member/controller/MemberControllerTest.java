package com.in2l.domain.member.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.member.repository.MemberRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
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
  void DBClean1() {
    memberRepository.deleteAllInBatch();
  }

  @AfterEach
  void DBClean2() {
    memberRepository.deleteAllInBatch();
  }

  String testEmail = "test@gmail.com";
  String testPassword = "Password123!@#";
  String testMembername = "고냥인";
  String testPhoneNumber = "0108811234";
  GenderType testGender = GenderType.MALE;
  LocalDateTime testBirthDay = LocalDateTime.now();
  String testAddress = "송파구";
  String testProfileImage = "/path/image.jpg";
}
//  @Test
//  @DisplayName("멤버 1명 GET")
//  void Test_get1Member() throws Exception {
//
//    //Given
//    Member member = Member.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .memberName(testMembername)
//        .build();
//
//    String json = objectMapper.writeValueAsString(member);
//
//    Member member1 = memberRepository.save(member);
//
//    //expected
//    mockMvc.perform(get("/v1/member/{memberId}", member1.getMember_id())
//        .contentType(MediaType.APPLICATION_JSON)
//        .contentType(json))
//        .andExpect(status().isOk())
//        .andDo(print());
//  }
//
//  @Test
//  @DisplayName("없는사람 1명 get할때 오류가 제대로 뜨는지(안뜬다 TODO)")
//  void Test_get1Member_butError() throws Exception {
//
//    //Given
//    Member member = Member.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .memberName(testMembername)
//        .build();
//
//    String json = objectMapper.writeValueAsString(member);
////
//    Member member1 = memberRepository.save(member);
//
//    //expected
//    mockMvc.perform(get("/v1/member/{memberId}", member1.getMember_id())
//        .contentType(MediaType.APPLICATION_JSON)
//        .contentType(json))
//        .andExpect(status().isOk()) //TODO: MemberNotFound exception과 "사용자를 찾을수 없습니다" 메세지를 테스트해야 하는데 안된다.
////        .andExpect(result -> assertTrue(result.getResolvedException() instanceof MemberNotFound))
////        .andExpect(result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(MemberNotFound.class)))
////        .andExpect(jsonPath("$.message").value("사용자를 찾을 수 없습니다."))
//        .andDo(print());
//
////import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//  }
//
//  @Test
//  @DisplayName("멤버 1명 삭제 확인.")
//  void Test_delete1Member() throws Exception {
//
//    //Given
//    Member member = Member.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .memberName(testMembername)
//        .build();
//
//    Member member1 = memberRepository.save(member);
//
//    //when
//    mockMvc.perform(delete("/v1/member/{memberId}", member1.getMember_id())
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.message").value("사용자가 삭제되었습니다."))
//        .andExpect(status().isOk())
//        .andDo(print());
//
//    //then
////    Assertions.assertEquals(member1.getMember_id(), 1L);
//
////import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//  }
//
//  @Test
//  @DisplayName("멤버 1명 생성 확인.")
//  void Test_post1Member() throws Exception {
//
//    //Given
//    MemberRequest memberRequest = MemberRequest.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .memberName(testMembername)
//        .gender(testGender)
//        .birthDay(testBirthDay)
//        .address(testAddress)
//        .profileImage(testProfileImage)
//        .build();
//
//    String json = objectMapper.writeValueAsString(memberRequest);
//
//    mockMvc.perform(post("/v1/member")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(json))
//        .andExpect(status().isOk())
//        .andDo(print());
//  }
//
//  @Test
//  @DisplayName("멤버 1명 프로필 업데이트 확인")
//  void Test_patch1Member() throws Exception {
//    //Given
//    Member member = Member.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .memberName(testMembername)
//        .build();
//
//    Member member1 = memberRepository.save(member);
//
//    //when
//    MemberRequest memberRequest = MemberRequest.builder()
//        .email("updatedEmail@gmail.com")
//        .password(testPassword)
//        .memberName("updated고냥인")
//        .address("updated송파구")
//        .profileImage("updated/path/")
//        .birthDay(LocalDateTime.now())
//        .gender(GenderType.MALE)
//        .phoneNumber("updated010812345")
//        .build();
//
//    mockMvc.perform(patch("/v1/member/{memberId}", member1.getMember_id())
//      .contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(memberRequest)))
//        .andExpect(status().isOk());
//  }
//}

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