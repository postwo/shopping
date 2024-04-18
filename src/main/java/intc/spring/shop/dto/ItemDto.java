package intc.spring.shop.dto;

import intc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {


    public Long id; //상품 아이디


    private String itemNm;  //상품 이름

    private int price;  //가격

    private int stockNumber;   //재고수량


    private String itemDetail; //상품상세 설명


    //이거는 Entity하고 다르게 String으로 한다
    private String itemSellStatus; //상품 판매 상태 (enum에서 상수)


    private LocalDateTime regTime; // 상품에 대한 등록시간


    private LocalDateTime updateTime;  // 상품 수정시간
}
