package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


  public void save(MemberDto memberDto) {
    MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
    memberRepository.save(memberEntity);
  }

  public MemberDto login(MemberDto memberDto) {
    /*
      1.회원이 입력한 이메일로 DB에서 조회를 함
      2.DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
    */
    Optional<MemberEntity> byMemberEmail =  memberRepository.findByMemberEmail(memberDto.getMemberEmail());
    if(byMemberEmail.isPresent()){
      //조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
      MemberEntity memberEntity = byMemberEmail.get();//get()메서드를 쓰면 안에 있는 객체를 가져올 수 있다.
      if(memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())){
        //비밀번호 일치
        //entity -> Dto변환 후 리턴
        MemberDto dto = MemberDto.toMemberDto(memberEntity);
        return dto;
      }else{
        //비밀번호 불일치(로그인 실패)
          return null;
      }
    }else{
      //조회 결과가 없다.
      return null;
    }
  }

  public List<MemberDto> findAll() {
    List<MemberEntity> memberEntityList = memberRepository.findAll();
    List<MemberDto> memberDtoList = new ArrayList<>();
    for(MemberEntity memberEntity : memberEntityList){
      memberDtoList.add(MemberDto.toMemberDto(memberEntity));
    }
    return memberDtoList;
  }

  public MemberDto findById(Long id) {
    Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
    if (optionalMemberEntity.isPresent()) {
      return MemberDto.toMemberDto(optionalMemberEntity.get());
    }
    return null;
  }

  public MemberDto updateForm(String myEmail) {
    Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
    if(optionalMemberEntity.isPresent()){
      return MemberDto.toMemberDto(optionalMemberEntity.get());
    }else{
      return null;
    }
  }

  public void update(MemberDto memberDto) {
    memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
  }

  public void deleteById(Long id) {
    memberRepository.deleteById(id);
  }

  public String emailCheck(String memberEmail) {
    Optional<MemberEntity> byMemberEntity = memberRepository.findByMemberEmail(memberEmail);
    if(byMemberEntity.isPresent()){
      //조회결과이가 있다 -> 사용할 수 없다.
      return null;
    }else{
      //조회결과가 없다 -> 사용할 수 있다.
      return "ok";
    }
  }
}
