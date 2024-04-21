package intc.spring.shop.service;

import intc.spring.shop.entity.Member;
import intc.spring.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberSerivce implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){ 
        validateDuplicationMember(member); //중복되었는지 확인
        return memberRepository.save(member); //이것도 타입이 member여서 이렇게 바로 반환할수 있다
    }

    private void validateDuplicationMember(Member member) {  //이미등록된 사용자인지 구별하기위해 만드는 메서드
        Optional<Member> findeMember = memberRepository.findByEmail(member.getEmail()); //Optional<Member> 을 사용한 이유는 메일이 반드시 있는게 아니여서 이렇게 optional로 감싼다
        if (findeMember.isPresent()) { //findeMember.isPresent() 멤버의 같은 이메일이 있다면
           // Member member1 = findeMember.get();// 1번 방식//존재하는 얘를 뽑아오기 위해 get을 사용 //get은 member타입이다
            System.out.println(findeMember.get().getEmail()); //2번방식 중복되는게 있는지 확인
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // UserDetailsService 구현받은거
    //loadUserByUsername(String email) 여기 매개변수는 변경가능
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


//        Optional<Member> member = memberRepository.findByEmail(email);
//        if (member.isPresent()){ //member.isPresent() member가 존재한다면  //member가 있을수도 있고 없을수도 있기 때문에 이렇게 조건문 사용
//         Member findMember =  member.get();//이렇게 해야 유저 한명을 뽑아온다
//        }else{ //맴버가 존재하지 않으면 예외를 넣어서 가르쳐준다
//             throw new UsernameNotFoundException(email+ "사용자 없음");
//        }

        //위 방식은 너무 길기 때문에 아래방식 사용
        //orElseThrow() 존재하지 않을경우 에러가 발생하는 경우이면
        //orElseThrow로 타입 이 T이기 때문에 결국에는 MemberEntity로 받으면된다 findByEmail에 타입인 oprional로 받을필요가 없다
        // 정상적으로 email을 뽀아올수 있으면 MemberEntity를 뽑아오는거고 그게 아니면 예외가 처리된다
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("해당 사용자가 없습니다"+email));

        log.info("=============== >[지금 로그인 사용자]"+member);

        return User.builder() //최소 3가지
                .username(member.getEmail())
                .password(member.getPassword()) //db에 암호화가 되어있기 때문에 그냥 가져다 쓰기
                .roles(member.getRole().toString()) //유저인지 어드민인지
                .build();
    }
}
