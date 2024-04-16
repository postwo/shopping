package intc.spring.shop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder// 객체를 만들수 있는 기능
public class UserDto {

    private int age;
    private String name;
}
