package com.example.demo.repository;

import com.example.demo.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  //이메일로 회원 정보 조회(select * from member_table where member_email=?)
  Optional<MemberEntity> findByMemberEmail(String memberEmail);//일종의 null방지를 위한 옵셔널 기본적으로 감싸서 넘겨준다.
}
