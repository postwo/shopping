package intc.spring.shop.entity;


import intc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder // 인자가 5개인데 2개만 만들고 싶을때 사용
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; //상품 아이디

    private String itemNm;  //상품 이름
    
    private int price;  //가격
    
    private int stockNumber;   //재고수량
    
    private String itemDetail; //상품상세 설명

    private ItemSellStatus itemSellStatus; //상품 판매 상태 (enum에서 상수)
}
