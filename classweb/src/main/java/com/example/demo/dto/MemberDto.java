package com.example.demo.dto;

import com.example.demo.entity.MemberEntity;
import lombok.*;

//각각의 필드에 대한 게터와 세터를 만들어준다
@Getter
@Setter
@NoArgsConstructor//기본생성자를 자동으로 만들어준다.
@AllArgsConstructor//모든 필드를 매개변수로 하는 생성자를 만들어준다.
@ToString//Dto클래스를 호출을 할때 toString()으로 호출을 하는대 그때 보내는 필드정의
public class MemberDto {//회원정보에 필요한 내용을 필드로 정의를 한다.
    private Long id;
    private  String memberEmail;
    private  String memberPassword;
    private  String memberName;

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setMemberEmail(memberEntity.getMemberEmail());
        memberDto.setMemberPassword(memberEntity.getMemberPassword());
        memberDto.setMemberName(memberEntity.getMemberName());
        return memberDto;
    }


}
