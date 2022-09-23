package com.in2l.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.domain.member.exception.MemberNotFound;
import com.in2l.domain.member.repository.MemberRepository;
import com.in2l.domain.order.domain.OrderProduct;
import com.in2l.domain.order.repository.OrdersProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
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

  @AfterEach
  void DBClean1() {
    memberRepository.deleteAllInBatch();
  }

//  @AfterEach
//  void DBClean2() {
//    ordersProductRepository.deleteAllInBatch();
//  }

  String testEmail = "test@gmail.com";
  String testPassword = "Password123!@#";
  String testName = "고냥인";
  String testPhoneNumber = "0108811234";
  GenderType testGender = GenderType.MALE;
  LocalDateTime testBirthDay = LocalDateTime.now();
  String testAddress = "송파구";
  String testProfileImage = "/path/image.jpg";
  Boolean testDeleteFlag = false;

  @Test
  @DisplayName("멤버 1명 GET")
  void getMember1() throws Exception{

    //Given
    Member member1 = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .name(testName)
        .deleteFlag(testDeleteFlag)
        .build();

    String memberJson = objectMapper.writeValueAsString(member1);

    memberRepository.save(member1);

    //expected
    mockMvc.perform(get("/v0/member/{id}", member1.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(memberJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value(testName))
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
        .name(testName)
        .build();

    String memberJson = objectMapper.writeValueAsString(member1);

    Member savedMember1 = memberRepository.save(member1);

    Long wrongid = member1.getId();

    //TODO: 이건 어떻게 해야 할까????
//    String message = memberNotFound.getMessage();
//    System.out.println("@@@@MGS::: "+ message);

    //expected
    mockMvc.perform(get("/v0/member/{id}", wrongid)
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(memberJson))
        .andExpect(status().isOk())
//        .andExpect(content().string("사용자를 찾을 수 없습니다."))
//        .andExpect(jsonPath("name").value(testname))
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
        .name(testName)
        .gender(testGender)
        .birthDay(testBirthDay)
        .address(testAddress)
        .profileImage(testProfileImage)
        .deleteFlag(testDeleteFlag)
        .build();

    String json = objectMapper.writeValueAsString(memberRequest);

    mockMvc.perform(post("/v0/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 프로필 업데이트")
  void patchMember() throws Exception {
    //Given
    Member member = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .name(testName)
        .deleteFlag(testDeleteFlag)
        .build();

    Member member1 = memberRepository.save(member);

    //when
    MemberRequest memberRequest = MemberRequest.builder()
        .email("updatedEmail@gmail.com")
        .password(testPassword)
        .name("updated고냥인")
        .address("updated송파구")
        .profileImage("updated/path/")
        .birthDay(LocalDateTime.now())
        .gender(GenderType.MALE)
        .phoneNumber("updated010812345")
        .deleteFlag(testDeleteFlag)
        .build();

    mockMvc.perform(patch("/v0/member/{id}", member1.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(memberRequest)))
        .andExpect(jsonPath("name").value("updated고냥인"))
        .andExpect(jsonPath("id").value(member1.getId()))   //여기가 핵심. id가 변경되면 안된다.
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 삭제 확인.")
  void deleteMember() throws Exception {
    //Given
    Member member = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .name(testName)
        .address(testAddress)
        .profileImage(testProfileImage)
        .birthDay(testBirthDay)
        .phoneNumber(testPhoneNumber)
        .gender(testGender)
        .build();

    Member member1 = memberRepository.save(member);

    //when
    mockMvc.perform(delete("/v0/member/{id}", member1.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(member1.getId()))
        .andExpect(jsonPath("$.message").value("사용자가 삭제되었습니다."))
        .andExpect(jsonPath("$.deleteFlag").value("true"))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 GET + delete가 아닌 사용자만.")
  void getMemberWithDeleteFlag() throws Exception, MemberNotFound {

    //TODO: Member Generator가 필요함.
    //Given
    Member member1 = Member.builder()
        .email(testEmail)
        .password(testPassword)
        .name(testName)
        .address(testAddress)
        .profileImage(testProfileImage)
        .birthDay(testBirthDay)
        .phoneNumber(testPhoneNumber)
        .gender(testGender)
        .deleteFlag(Boolean.FALSE)
        .build();

    Member deletedMember = Member.builder()
        .email("deleted@gmail.com")
        .password(testPassword)
        .name("삭제된사용자.")
        .address(testAddress)
        .profileImage(testProfileImage)
        .birthDay(testBirthDay)
        .phoneNumber(testPhoneNumber)
        .gender(GenderType.UNKNOWN)
        .deleteFlag(Boolean.TRUE)
        .build();

    Member member = memberRepository.save(member1);//TODO: 이거 list로 저장 필요?
    Member deletedSavedMember = memberRepository.save(deletedMember);

//    String memberJson = objectMapper.writeValueAsString(deletedMember);

    mockMvc.perform(get("/v0/member/{id}", deletedSavedMember.getId())
        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().is5xxServerError())
//        .andExpect(jsonPath("$.code").value("500"))
        .andExpect(jsonPath("$.message").value("사용자를 찾을 수 없습니다."))
//        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("Delete되지 않은 멤버만 GET")
  void getMembers() throws Exception{
    //Given
    List<Member> requestMembers = IntStream.range(0, 7)
        .mapToObj( i -> {
          return Member.builder()
              .email(testEmail + i)
              .password(testPassword)
              .name(testName + i)
              .deleteFlag(testDeleteFlag)
              .phoneNumber(testPhoneNumber)
              .build();
        })
        .collect(Collectors.toList());

    memberRepository.saveAll(requestMembers);


    //Given
    List<Member> DeletedMembers = IntStream.range(8, 14)
        .mapToObj( i -> {
          return Member.builder()
              .email(testEmail + i)
              .password(testPassword)
              .name(testName + i)
              .deleteFlag(true)
              .phoneNumber(testPhoneNumber)
              .build();
        })
        .collect(Collectors.toList());

    memberRepository.saveAll(requestMembers);

    //expected
    mockMvc.perform(get("/v0/member")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(7)))    //전체 몇개인지
        .andDo(print());
  }
}