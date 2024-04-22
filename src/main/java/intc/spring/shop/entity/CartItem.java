package intc.spring.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //cartitem이 m이다
    @JoinColumn(name="cart_id")
    private Cart cart; //카트 하나에 여러물건이 실을수 있다

    @ManyToOne(fetch = FetchType.LAZY) //cartitem이 m이다
    @JoinColumn(name="item_id")
    private Item item;

    private int count;
}
