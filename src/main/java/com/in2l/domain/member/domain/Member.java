package com.in2l.domain.member.domain;

import com.in2l.domain.member.dto.request.MemberEdit;
import com.in2l.domain.member.dto.request.MemberRequest;
import com.in2l.global.common.domain.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

  @Id
//  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String email;

  @NotBlank   //TODO: regetx valid 넣어야함 (memberEditor에 있음)
  private String password;

  @NotBlank
  private String name;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private GenderType gender;      //TODO: 젠더 validation 도 필요함.

  private LocalDateTime birthDay;

  private String address;

  private String profileImage;

  @Column(columnDefinition = "boolean default false")
//  @NotBlank   TODO: 이건 null 이면 안되고, 디폴트로 false로 넣어야 한다는건데 이런게 가능?
  private Boolean deleteFlag;     //TODO: 근데 왠지 delete바뀐 날짜는 어딘가 박아 넣어야 할듯? (개인정보이슈?)

  @Builder
  public Member(@NotBlank String email,
      @NotBlank String password, @NotBlank String name, String phoneNumber,
      GenderType gender, LocalDateTime birthDay, String address, String profileImage,Boolean deleteFlag) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    this.birthDay = birthDay;
    this.address = address;
    this.profileImage = profileImage;
    this.deleteFlag = deleteFlag;
  }

  public static Member of(MemberRequest memberRequest) {
    return Member.builder()
        .email(memberRequest.getEmail())
        .password(memberRequest.getPassword())
        .name(memberRequest.getName())
        .phoneNumber(memberRequest.getPhoneNumber())
        .gender(memberRequest.getGender())
        .birthDay(memberRequest.getBirthDay())
        .address(memberRequest.getAddress())
        .profileImage(memberRequest.getProfileImage())
        .deleteFlag(memberRequest.getDeleteFlag())
        .build();
  }

  public MemberEditor.MemberEditorBuilder toEditor(){
    MemberEditor.MemberEditorBuilder builder = MemberEditor.builder()
        .email(email)
        .password(password)
        .name(name)
        .phoneNumber(phoneNumber)
        .gender(gender)
        .birthDay(birthDay)
        .address(address)
        .profileImage(profileImage)
        .deleteFlag(deleteFlag);

    return builder; //빌더 자체를 보냄.
  }

  public void edit(MemberEdit memberEdit){
    email = memberEdit.getEmail();
    password = memberEdit.getPassword();
    name = memberEdit.getName();
    phoneNumber = memberEdit.getPhoneNumber();
    gender = memberEdit.getGender();
    birthDay = memberEdit.getBirthDay();
    address = memberEdit.getAddress();
    profileImage = memberEdit.getProfileImage();
    deleteFlag = memberEdit.getDeleteFlag();
  }

  //validation은 MemberRequest 에서
//  public void validate(){
//    if( title.contains("바보")){
//      throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
//      //exception 안에 이런 정보를 포함시켜 놓고,
//    }
//  }
}


