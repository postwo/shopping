package intc.spring.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymleafController {

    @GetMapping("/thymleaf/ex1")
    public String ex1(){
        System.out.println("123");
        return "thymeleaf/ex1";
    }

//    @GetMapping("/thymleaf/ex1")
//    public String ex1(){
//        return "thymeleaf/ex1";
//    }

}
