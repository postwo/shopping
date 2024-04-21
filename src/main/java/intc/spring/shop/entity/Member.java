package intc.spring.shop.entity;

import intc.spring.shop.constant.Role;
import intc.spring.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true) //중복되지 않게
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;


    //MemberDto를 Memberentity로 변경
    //dto에서 정보를가지고 와서 entity에 set해준거다
    public static Member createMember(MemberFormDto dto, BCryptPasswordEncoder passwordEncoder){
        return Member.builder()
                .role(Role.USER)
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .address(dto.getAddress())
                .build();
    }
}
