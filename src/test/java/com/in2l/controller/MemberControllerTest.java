package com.in2l.controller;

import static org.hamcrest.Matchers.hasSize;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles({"test"})
//@RequestMapping(value = "${apiVersion}/member")   //TODO: 짱나네 이거 왜못씀?!?!
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

  String testEmail = "test@gmail.com";
  String testPassword = "Password123!@#";
  String testName = "고냥인";
  String testPhoneNumber = "0108811234";
  GenderType testGender = GenderType.MALE;
  String testBirthDay = "1985-02-21";
  String testAddress = "송파구";
  String testProfileImage = "/path/image.jpg";
  Boolean testDeleteFlag = false;

  String apiPath;

  @Value("${apiVersion}")
  public void setApiPath(String apiPath) {
    this.apiPath = apiPath;
  }

  @Test
  @DisplayName("멤버 1명 GET")
  void getMember1() throws Exception{

    //Given
    MemberRequest memberRequest = MemberGenerator();

    Member savedMember = memberRepository.save(Member.of(memberRequest));

    //expected
    //${apiVersion}/member/{id}
//    Request URI = /api/v0/member/1
    mockMvc.perform(get("/api/v0/member/{id}", savedMember.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(objectMapper.writeValueAsString(savedMember)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value(testName))
        .andExpect(jsonPath("email").value(testEmail))
        .andDo(print());
  }
//
  @Test
  @DisplayName("TODO: 없는사람 1명 get할때 오류가 제대로 뜨는지(구현해야함)")
  void getMemberButNoExsist() throws Exception{
    //Given
    MemberRequest memberRequest = MemberGenerator();
    Member savedMember = memberRepository.save(Member.of(memberRequest));;

    Long wrongid = savedMember.getId()+1;

    //expected
    mockMvc.perform(get("/api/v0/member/{id}", wrongid)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("message").value("사용자를 찾을 수 없습니다."))
        .andDo(print());
//        .andExpect(jsonPath("name").value(testname))
//        .andExpect(jsonPath("email").value(testEmail))
  }

  @Test
  @DisplayName("멤버 1명 생성")
  void postMember() throws Exception {
    //Given
    String memberJson = objectMapper.writeValueAsString(MemberGenerator());

    mockMvc.perform(post("/api/v0/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content(memberJson))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 생성시 validation체크.")
  void postMemberWithValidation() throws Exception {
    //Given
    String memberJson = objectMapper.writeValueAsString(MemberGeneratorWithWrong());

    mockMvc.perform(post("/api/v0/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content(memberJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("message").value("대화명에 비속어는 포함할 수 없습니다!!"))
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 프로필 업데이트")
  void patchMember() throws Exception {
    //Given
    MemberRequest memberRequest = MemberGenerator();
    Member savedMember = memberRepository.save(Member.of(memberRequest));

    //when
    MemberRequest newMemberRequest = MemberRequest.builder()
        .email("updatedEmail@gmail.com")
        .password(testPassword)
        .name("updated고냥인")
        .address("updated송파구")
        .profileImage("updated/path/")
        .birthDay("1982-09-03")
        .gender(GenderType.MALE)
        .phoneNumber("updated010812345")
        .deleteFlag(testDeleteFlag)
        .build();

    mockMvc.perform(patch("/api/v0/member/{id}", savedMember.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newMemberRequest)))
        .andExpect(jsonPath("name").value("updated고냥인"))
        .andExpect(jsonPath("id").value(savedMember.getId()))   //여기가 핵심. id가 변경되면 안된다.
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("멤버 1명 프로필 업데이트 validation체크")
  void patchMemberWithValidation() throws Exception {
    //Given
    MemberRequest memberRequest = MemberGenerator();
    Member savedMember = memberRepository.save(Member.of(memberRequest));

    //when
    MemberRequest newMemberRequest = MemberGeneratorWithWrong();

    mockMvc.perform(patch("/api/v0/member/{id}", savedMember.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newMemberRequest)))
        .andExpect(jsonPath("message").value("대화명에 비속어는 포함할 수 없습니다!!"))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }


  @Test
  @DisplayName("멤버 1명 삭제: (사용안함)")
  void DeleteMember() throws Exception {

    //Given
    MemberRequest memberRequest = MemberGenerator();
    Member savedMember = memberRepository.save(Member.of(memberRequest));

    //expected
    mockMvc.perform(delete("/api/v0/member/{id}", savedMember.getId())
        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.id").value(savedMember.getId()))
//        .andExpect(jsonPath("$.message").value("사용자가 삭제되었습니다."))
//        .andExpect(jsonPath("$.deleteFlag").value("true"))
        .andExpect(status().isOk())
        .andDo(print());
  }
//
//
//
  @Test
  @DisplayName("멤버 1명 삭제 확인.")
  void updateDeleteFlagMember() throws Exception {
    //Given
    MemberRequest memberRequest = MemberGenerator();
    Member savedMember = memberRepository.save(Member.of(memberRequest));

    //when
    MemberRequest newMemberRequest = MemberRequest.builder()
        .email(testEmail)     //deleteFlag만 전달하게 고도화도 가능함.
        .password(testPassword)
        .name(testName)
        .phoneNumber(testPhoneNumber)
        .gender(testGender)
        .birthDay("1985-02-21")
        .address(testAddress)
        .profileImage(testProfileImage)
        .deleteFlag(true)   //이거 하나 갱신
        .build();

    mockMvc.perform(patch("/api/v0/member/{id}", savedMember.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newMemberRequest)))
        .andExpect(jsonPath("deleteFlag").value("true"))
        .andExpect(jsonPath("message").value("Member: "+savedMember.getEmail()+" 의 delete가 처리되었습니다."))
        .andExpect(jsonPath("id").value(savedMember.getId()))   //여기가 핵심. id가 변경되면 안된다.
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("Delete되지 않은 멤버만 GET")
  void getMembers() throws Exception{
    //Given1
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

    //Given2
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
    mockMvc.perform(get("/api/v0/member")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(7)))    //전체 몇개인지
        .andDo(print());
  }

  private MemberRequest MemberGenerator() {
    return MemberRequest.builder()
        .email(testEmail)
        .password(testPassword)
        .name(testName)
        .phoneNumber(testPhoneNumber)
        .gender(testGender)
        .birthDay(testBirthDay)
        .address(testAddress)
        .profileImage(testProfileImage)
        .deleteFlag(testDeleteFlag)
        .build();
  }

  private MemberRequest MemberGeneratorWithWrong() {
    return MemberRequest.builder()
        .email(testEmail)
        .password(testPassword)
        .name("sucksthisworld")
        .phoneNumber(testPhoneNumber)
        .gender(testGender)
        .birthDay(testBirthDay)
        .address(testAddress)
        .profileImage(testProfileImage)
        .deleteFlag(testDeleteFlag)
        .build();
  }



//  private Member MemberGenerator() {
//    return Member.builder()
//        .email(testEmail)
//        .password(testPassword)
//        .name(testName)
//        .phoneNumber(testPhoneNumber)
//        .gender(testGender)
//        .birthDay(testBirthDay)
//        .address(testAddress)
//        .profileImage(testProfileImage)
//        .deleteFlag(testDeleteFlag)
//        .build();
//  }

//  @Test
//  @DisplayName("모든 멤버: delete여부 노상관. 이건 service 레벨에서 해야함.")
//  void getMemberAll() throws Exception, MemberNotFound {
//
//    //TODO: Member Generator가 필요함.
//    //Given
//    Member member1 = MemberGenerator();
//
//    Member deletedMember = Member.builder()
//        .email("deleted@gmail.com")
//        .password(testPassword)
//        .name("삭제된사용자.")
//        .address(testAddress)
//        .profileImage(testProfileImage)
//        .birthDay(testBirthDay)
//        .phoneNumber(testPhoneNumber)
//        .gender(GenderType.UNKNOWN)
//        .deleteFlag(Boolean.TRUE)
//        .build();
//
//    Member member = memberRepository.save(member1);
//    Member deletedSavedMember = memberRepository.save(deletedMember);
//
//    mockMvc.perform(get("/v0/member/{id}", deletedSavedMember.getId())
//        .contentType(MediaType.APPLICATION_JSON))
////        .andExpect(status().is5xxServerError())
////        .andExpect(jsonPath("$.code").value("500"))
//        .andExpect(jsonPath("$.*", hasSize(2)))    //전체 몇개인지
////        .andExpect(status().isOk())
//        .andDo(print());
//  }

}