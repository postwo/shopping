package intc.spring.shop;

import intc.spring.shop.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@Controller
public class HelloController {

//    @GetMapping("/") // restcontroller
//    public Point hello(){
//        Point p = new Point(10,20); //key와 value 값으로 나온다 =jason형태이다
//        return p;
//    }



    @GetMapping("/")
    public @ResponseBody UserDto hello(){ //name과 age하나만 만들때
       // UserDto user = UserDto.builder().age(10).build(); //이방식으로 하나씩 가지고 있는걸 만들수 있다
        //UserDto user = UserDto.builder().age(10).name("김수").build(); //이렇게 순서 상관없이 생성가능

        UserDto user = new UserDto();
        user.setAge(10);
        user.setName("나는");

        return user;
    }
}
