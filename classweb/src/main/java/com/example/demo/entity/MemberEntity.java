package com.example.demo.entity;

import com.example.demo.dto.MemberDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String memberPhonenumber;

  @Column(unique = true)
  private String memberEmail;

  @Column
  private String memberPassword;

  @Column
  private String memberBirthday;



  public static MemberEntity toMemberEntity(MemberDto memberDto) {
    MemberEntity entity = new MemberEntity();
    entity.setMemberEmail(memberDto.getMemberEmail());
    entity.setMemberPhonenumber(memberDto.getMemberPhonenumber());
    entity.setMemberPassword(memberDto.getMemberPassword());
    entity.setMemberBirthday(memberDto.getMemberBirthday());
    return entity;
  }

  public static MemberEntity toUpdateMemberEntity(MemberDto memberDto) {
    MemberEntity entity = new MemberEntity();
    entity.setId(memberDto.getId());
    entity.setMemberEmail(memberDto.getMemberEmail());
    entity.setMemberPhonenumber(memberDto.getMemberPhonenumber());
    entity.setMemberBirthday(memberDto.getMemberBirthday());
    entity.setMemberPassword(memberDto.getMemberPassword());
    return entity;
  }
}
