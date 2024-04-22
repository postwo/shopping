package intc.spring.shop.entity;

import intc.spring.shop.common.entity.BaseEntity;
import intc.spring.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // db테이틀에서 이름을 이렇게 지정 가능
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime order_date; //주문 날짜
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

// 한꺼번에 처리할거여서 주석처리
//    private LocalDateTime regTime; // 상품에 대한 등록시간
//    private LocalDateTime updateTime;  // 상품 수정시간


    //오더입장에서느 오더아이템이 누구인지 모르니까 연관관계를 걸도록 필드를 만든다
    //오더에서는 오더아이템하고 OneToMany 관계이다
    //mappedBy = "order" 는 오터아이템에서 oeder이다 일은 오더아아템에서 다한다
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")는 "한 개의
    주문에 여러 개의 주문 상품이 존재한다"는 관계를 나타내는데 사용됩니다. 여기서
    fetch = FetchType.LAZY는 연관된 엔티티들이 실제로 사용될 때까지 로딩을 지연시키는
    것을 의미합니다. 즉, Order 엔티티가 로딩될 때 OrderItem 엔티티들은 로딩되지 않고,
    실제로 orderItems 필드에 접근할 때 로딩됩니다.
    mappedBy = "order"는 연관 엔티티 클래스인 OrderItem 클래스 내에서의 매핑을 지정합니다.
    여기서 "order"는 OrderItem 클래스 내에 있는 Order 엔티티를 가리키는 필드의 이름입니다.
    마지막으로, private List<OrderItem> orderItems = new ArrayList<>();는 Order 엔티티 클래스
    내의 orderItems 필드를 초기화하고 있습니다. 이 필드는 Order 엔티티와 OrderItem 엔티티 간의 일대다
    관계를 표현하는데 사용됩니다. ArrayList로 초기화된 이유는 해당 필드가 여러 개의 OrderItem 엔티티를 담을 수
    있도록 하기 위함*/
    //cascade = CascadeType.ALL 이걸사용하면 없애면 없애고 수정하면 수정하고
    //orphanRemoval = true)고아 객체가 되면 제거
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order"
            ,cascade = CascadeType.ALL,orphanRemoval = true) //기본전략이 Lazy이다
    private List<OrderItem> orderItems =new ArrayList<>();
}
