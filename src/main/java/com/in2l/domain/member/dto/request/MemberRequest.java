package com.in2l.domain.member.dto.request;

import com.in2l.domain.member.domain.GenderType;
import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.exception.InvalidMemberRequest;
import com.in2l.global.common.domain.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor    //이거 없으면 json오류 났었음.
@ToString
public class MemberRequest{

  //validation은 여기서 다 쳐야한다.
  /**
   * @email            :String
   * @password     :String
   * @memberName    :String
   * phoneNumber  :String? Int?
   * gender         :ENUM
   * birthDay        :DateTime
   * address       :String
   * profileImage :String
   */

  @NotBlank
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  private String email;

  @NotBlank
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
      message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
  private String password;

  @NotBlank
//  @Max(50) @Min(1)      //걍 생각없이 50으로 잡음.
  //TODO: Validation fail하면 Exception 출력하게 해야함.
  private String memberName;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private GenderType gender;

//  @DateTimeFormat
  private LocalDateTime birthDay;

  private String address;

  private String profileImage;      //TODO: 확장자 제한 있어야함.

  @Builder            //TODO: builder가 필요 없지. 근데 테스트땜에 있어야 하나??
  public MemberRequest(
      @NotBlank @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
      @NotBlank @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
          message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.") String password,
      @NotBlank String memberName, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage) {
    this.email = email;
    this.password = password;
    this.memberName = memberName;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
  }

  public static Member of(MemberRequest memberRequest) {
    Member member1 = Member.builder()
        .email(memberRequest.getEmail())
        .password(memberRequest.getPassword())    //TODO: 암호화 필요.
        .memberName(memberRequest.getMemberName())
        .phoneNumber(memberRequest.getPhoneNumber())
        .gender(memberRequest.getGender())
        .birthDay(memberRequest.getBirthDay())    //TODO: Front에서 받아온거 LocalDateTime으로 파싱 필요.
        .address(memberRequest.getAddress())
        .profileImage(memberRequest.getProfileImage())
        .build();

    return member1;
  }

  public void validate() {
    if (memberName.contains("fuck")) {
      throw new InvalidMemberRequest("대화명에 비속어는 포함할 수 없습니다!!");
    }
  }
}