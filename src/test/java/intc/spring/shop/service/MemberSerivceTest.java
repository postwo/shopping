package intc.spring.shop.service;

import intc.spring.shop.dto.MemberFormDto;
import intc.spring.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //이걸 걸면 db에 저장되지않고 한세트 끝나면 롤백시킨다
class MemberSerivceTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MemberSerivce memberSerivce;

    public Member createMember(){ // 계정하나 생성
        MemberFormDto dto= MemberFormDto.builder()
                .address("인천시 미추홀구")
                .email("test@naver.com")
                .password("1234")
                .name("홍길동")
                .build();

        //위에 내용들을 담아서 createMember를 호출
        Member member = Member.createMember(dto,passwordEncoder); //저자되기전 member

        return member;
    }


    @Test
    void saveMemberTest() {
      Member member =  createMember();
        System.out.println(member);
        Member member1= memberSerivce.saveMember(member); //저장후 member1
        System.out.println(member1);

    }

    @DisplayName("중복 회원 예외 발생 테스트")
    @Test
    void saveMemberTest2() {
        Member member1 =  createMember();
        Member member2 =  createMember();
        memberSerivce.saveMember(member1); //이거는 정상적으로 데이터 저장
        
      Throwable e = assertThrows(IllegalStateException.class, ()-> {
          memberSerivce.saveMember(member2); //이걸로 중복저장 으로 예외 테스트
        }); // IllegalStateException 이예외를 발생 시킬건데 이거를 발생시키는 거는 memberSerivce.saveMember(member2); 여기서 예외를 발생 시킬거다
        
        assertEquals("이미 존재하는 회원입니다.",e.getMessage()); //"이미 존재하는 회원입니다.",e.getMessage() 두개가 같으면 통과한다

    }




}