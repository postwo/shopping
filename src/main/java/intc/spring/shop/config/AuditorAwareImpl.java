package intc.spring.shop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() { //감시되어야 하는 사용자정보를 가지고온다
        //로그인 된정보를 가지고 온거다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//인증정보를 가져온거다
        String userId = ""; //유저아이디가 있을수도 없을수도 있기때문에 optional타입으로 받는거다
        if (authentication != null){ //인증이 되면 if문 동작
            userId = authentication.getName(); //로그인할때 쓴 아이디인 이메일을 가지고 온다
        }
        return Optional.of(userId);//of방식으로 날려줘도 된다 //empty() 이걸로 날려도 되고
    }

}
