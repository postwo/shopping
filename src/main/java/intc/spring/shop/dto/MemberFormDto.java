package intc.spring.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberFormDto { //@NotBlank,@NotEmpty 밸리데이션을 넣어서 처리
    /*@NotBlank는 null 체크 및 문자열의 경우 길이 0 및 빈 문자열(" ")검사
    , @NotEmpty는 null체크 및 문자열의 경우 길이 0인지 검사*/

    @NotBlank(message = "이름은 필수입력 입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입력 입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입력 입니다.")
    @Length(min = 4 , max=16 , message = "최소 4자이상 최대 16자이하 입니다")
    private String password;

    @NotEmpty(message = "주소는 필수입력 입니다.")
    private String address;




}
