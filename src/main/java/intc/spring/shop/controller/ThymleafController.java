package intc.spring.shop.controller;

import intc.spring.shop.dto.ItemDto;
import intc.spring.shop.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@Controller
@Slf4j //log
@RequestMapping("/thymeleaf")
public class ThymleafController {

    @GetMapping("/ex1")
    public String ex1(Model model){
        ItemDto itemDto = ItemDto.builder()
                .itemNm("피파 24")
                .itemDetail("피파 24.240")
                .itemSellStatus("SELL")// 이거는 문자열로 넣을거라서 내각 직접입력
                .price(20000)
                .build();

        model.addAttribute("itemDto",itemDto);
        return "thymeleaf/ex1";
    }

    @GetMapping("/ex2")
    public String ex2(){
        return "thymeleaf/ex2";
    }


    @GetMapping("/ex3")
    public String ex3(ParamDto dto,Model model) {
        log.info("===============dt0: "+dto);
        model.addAttribute("dto",dto);
        return "thymeleaf/ex3";
    }


//    @GetMapping("/ex4")
//    public String ex4(){
//
//        return "thymeleaf/ex4";
//    }

}
