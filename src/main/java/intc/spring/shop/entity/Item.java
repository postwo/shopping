package intc.spring.shop.entity;


import intc.spring.shop.common.entity.BaseEntity;
import intc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder // 인자가 5개인데 2개만 만들고 싶을때 사용
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    public Long id; //상품 아이디

    @Column(nullable = false,length = 50)
    private String itemNm;  //상품 이름
    
    private int price;  //가격
    
    private int stockNumber;   //재고수량
    
    @Lob  //대용량 관리
    @Column(nullable = false )
    private String itemDetail; //상품상세 설명

    @Enumerated(EnumType.STRING) // eumtype은 이렇게 사용한다
    private ItemSellStatus itemSellStatus; //상품 판매 상태 (enum에서 상수)


//    private LocalDateTime regTime; // 상품에 대한 등록시간
//
//
//    private LocalDateTime updateTime;  // 상품 수정시간
}
