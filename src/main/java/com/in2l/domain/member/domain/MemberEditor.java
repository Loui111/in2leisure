package com.in2l.domain.member.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberEditor {

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

  @Id
  private Long member_id;

  @NotBlank
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  private String email;

  @NotBlank
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
      message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
  private String password;

  @NotBlank
  @Max(50) @Min(1)      //걍 생각없이 50으로 잡음.
  private String memberName;

  private String phoneNumber;

  private GenderType gender;

  private LocalDateTime birthDay;

  private String address;

  private String profileImage;      //TODO: 확장자 제한 있어야함.

  @Builder
  public MemberEditor(
      @NotBlank @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
      @NotBlank @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
          message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.") String password,
      @NotBlank @Max(50) @Min(1) String memberName, String phoneNumber,
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
}
