package intc.spring.shop.service;

import intc.spring.shop.entity.Member;
import intc.spring.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSerivce { 

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){ 
        validateDuplicationMember(member);
        return memberRepository.save(member); //이것도 타입이 member여서 이렇게 바로 반환할수 있다
    }

    private void validateDuplicationMember(Member member) {  //이미등록된 사용자인지 구별하기위해 만드는 메서드
        Optional<Member> findeMember = memberRepository.findByEmail(member.getEmail());
        if (findeMember.isPresent()) { //findeMember.isPresent() 멤버의 같은 이메일이 있다면
           // Member member1 = findeMember.get();// 1번 방식//존재하는 얘를 뽑아오기 위해 get을 사용 //get은 member타입이다
            System.out.println(findeMember.get().getEmail()); //2번방식 중복되는게 있는지 확인
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

}
