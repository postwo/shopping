package intc.spring.shop.entity;

import intc.spring.shop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    //fk로 member_id를 받아온다
    //eager 사용할려는 테이블을 다 올린다(그러므로 성능이 떨어진다)
    //lazy 필요할때만 테이블을 올린다 (이게 성능이 좋다)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
