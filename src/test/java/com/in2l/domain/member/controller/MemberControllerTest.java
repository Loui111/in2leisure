package com.in2l.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

  @Test
  @DisplayName("/정상인지 확인.")
  void test() throws Exception{

    //Given
    Member member = Member.builder()
        .member_id(1L)
        .email("test@gmail.com")
        .password("Password123!@#")
        .memberName("고냥인")
        .build();

    String json = objectMapper.writeValueAsString(member);

    memberRepository.save(member);

    //expected
    mockMvc.perform(get("/member/1")
    .contentType(MediaType.APPLICATION_JSON)
    .contentType(json))
        .andExpect(status().isOk())
      .andDo(print());

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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