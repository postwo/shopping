package intc.spring.shop.controller;

import intc.spring.shop.dto.MemberFormDto;
import intc.spring.shop.entity.Member;
import intc.spring.shop.service.MemberSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberSerivce memberSerivce;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/member/new")
    public String MemberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());//빈객체를 생성해서 날린다
        return "member/memberForm";
    }

    @PostMapping("/member/new")
    public String insertMember(MemberFormDto dto){
        log.info("================> memberformdro"+dto);
       Member member = Member.createMember(dto,passwordEncoder); //암호화된 비밀번호를 넣어준다
        memberSerivce.saveMember(member);

        return "redirect:/"; //루트 경로 이동
    }


}
