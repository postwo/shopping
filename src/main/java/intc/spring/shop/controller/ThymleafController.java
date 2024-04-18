package intc.spring.shop.controller;

import intc.spring.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;

@Controller
public class ThymleafController {

    @GetMapping("/thymleaf/ex1")
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

//    @GetMapping("/thymleaf/ex1")
//    public String ex1(){
//        return "thymeleaf/ex1";
//    }

}
