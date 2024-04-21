package intc.spring.shop.controller;

import intc.spring.shop.dto.MemberFormDto;
import intc.spring.shop.entity.Member;
import intc.spring.shop.service.MemberSerivce;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberSerivce memberSerivce;
    private final BCryptPasswordEncoder passwordEncoder;

    //회원가입
    @GetMapping("/member/new")
    public String MemberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());//빈객체를 생성해서 날린다
        return "member/memberForm";
    }

    /*@Valid , BindingResult bindingResult  이두개는 세트이다*/
    @PostMapping("/member/new")
    public String insertMember(@Valid MemberFormDto dto, 
                               BindingResult bindingResult
                                ,Model model){ //@valid 밸리데이션 체크

        if (bindingResult.hasErrors()){ //내가 할려고 했던거에서 밸리데인션 조건중에 아무거나 걸리면 에러가 발생
           return "member/memberForm";
        }

        //문제(사용자가 중복된경우)가 있을수도 있기 때문에 try catch를 사용
        try {
            log.info("================> memberformdro"+dto);
            Member member = Member.createMember(dto,passwordEncoder); //암호화된 비밀번호를 넣어준다
            memberSerivce.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage()); //e로 부터 만들어진 메시지를 앞단에 보낸다
            return "member/memberForm";
        }



        return "redirect:/"; //루트 경로 이동
    }


    //로그인
    @GetMapping("/member/login")
    public String loginForm(){
        return "member/memberLoginForm";
    }

    //로그아웃
    @GetMapping("/member/logout")
    public String logoutForm(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//인증된정보를 들고온다 =로그인이 되어있으면 그정보를 authentication에 담아준다
        if (authentication != null){ //로그인을 했을수도 안했을수도 있기 때문에
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/";
    }


}
