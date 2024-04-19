package intc.spring.shop.service;

import intc.spring.shop.dto.MemberDto;
import intc.spring.shop.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberSerivceTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MemberSerivce memberSerivce;

    public Member createMember(){
        MemberDto dto= MemberDto.builder()
                .address("인천시 미추홀구")
                .email("test@naver.com")
                .password("1234")
                .name("홍길동")
                .build();

        //위에 내용들을 담아서 createMember를 호출
        Member member = Member.createMember(dto,passwordEncoder); //저자되기전 member
        System.out.println(member);

        Member member1= memberSerivce.saveMember(member); //저장후 member1
        System.out.println(member1);

        return member1;
    }


    @Test
    void saveMemberTest() {
      Member member =  createMember();

    }
}