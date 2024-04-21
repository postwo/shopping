package intc.spring.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberFormDto {

    private String name;
    private String email;
    private String password;
    private String address;

}
