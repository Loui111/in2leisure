package com.in2l.controller;

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
import com.in2l.domain.member.repository.MemberRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles({"test"})
class MemberControllerTest {

  @Autowired
  private ObjectMapper objectMapper;    //json용

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MemberRepository memberRepository;

//  @Autowired
//  private MemberNotFound memberNotFound;

  @BeforeEach
  void DBClean1() {
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

  @Test
  @DisplayName("멤버 1명 GET")
  void getMember1() throws Exception{
    //Given
    Member member1 = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .build();

    String memberJson = objectMapper.writeValueAsString(member1);

    Member savedMember1 = memberRepository.save(member1);

    //expected
    mockMvc.perform(get("/v0/member/{memberId}", member1.getMember_id())
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(memberJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("memberName").value(testMembername))
        .andExpect(jsonPath("email").value(testEmail))
        .andDo(print());
  }

  @Test
  @DisplayName("TODO: 없는사람 1명 get할때 오류가 제대로 뜨는지(구현해야함)")
  void getMemberButNoExsist() throws Exception{
    //Given
    Member member1 = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .build();

    String memberJson = objectMapper.writeValueAsString(member1);

    Member savedMember1 = memberRepository.save(member1);

    Long wrongMemberId = member1.getMember_id();

    //TODO: 이건 어떻게 해야 할까????
//    String message = memberNotFound.getMessage();
//    System.out.println("@@@@MGS::: "+ message);

    //expected
    mockMvc.perform(get("/v0/member/{memberId}", wrongMemberId)
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(memberJson))
        .andExpect(status().isOk())
//        .andExpect(content().string("사용자를 찾을 수 없습니다."))
//        .andExpect(jsonPath("memberName").value(testMembername))
//        .andExpect(jsonPath("email").value(testEmail))
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 생성")
  void postMember() throws Exception {

    //Given
    MemberRequest memberRequest = MemberRequest.builder()
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .gender(testGender)
        .birthDay(testBirthDay)
        .address(testAddress)
        .profileImage(testProfileImage)
        .build();

    String json = objectMapper.writeValueAsString(memberRequest);

    mockMvc.perform(post("/v0/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("TODO: 멤버 1명 프로필 업데이트(구현해야함)")
  void patchMember() throws Exception {
    //Given
    Member member = Member.builder()
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

    mockMvc.perform(patch("/v0/member/{memberId}", member1.getMember_id())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(memberRequest)))
        .andExpect(jsonPath("memberName").value("updated고냥인"))
//        .andExpect(jsonPath("member_id").value(member1.getMember_id()))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("멤버 1명 삭제 확인.")
  void deleteMember() throws Exception {
    //Given
    Member member = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .memberName(testMembername)
        .build();

    Member member1 = memberRepository.save(member);

    //when
    mockMvc.perform(delete("/v0/member/{memberId}", member1.getMember_id())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("사용자가 삭제되었습니다."))
        .andExpect(status().isOk())
        .andDo(print());
  }
}