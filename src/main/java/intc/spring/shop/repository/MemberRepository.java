package intc.spring.shop.repository;

import intc.spring.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //email 검색
    //Optional을 통해 있을수도 있고 없을수도 있고로 판단해서 정보를 받아온다
    Optional<Member> findByEmail(String email); //email로 조회해서 사용자가 무조건 있는게 아니기 때문에 optional을 붙여둔다
}
