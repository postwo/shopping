package intc.spring.shop.dto;

import intc.spring.shop.constant.ItemSellStatus;
import intc.spring.shop.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemFormDto { //화면단에서 정보를 가지고 온거다

    public Long id; //상품 아이디

    @NotBlank(message = "상품명은 필수 입력 입니다.")
    private String itemNm;  //상품 이름

    @NotNull(message = "가격은 필수 입력 입니다")
    private int price;  //가격

    @NotNull(message = "수량은 필수 입력 입니다")
    private int stockNumber;   //재고수량

    @NotBlank(message = "상품에 대한 설명은 필수 입력 입니다")
    private String itemDetail; //상품상세 설명


    //여러가지 형태를가질수 있기때문에 String을 사용안한다
    private ItemSellStatus itemSellStatus; //상품 판매 상태 (enum에서 상수)
    
    //이미지 dto가 여러개 들어있다
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    
    //아이디만 관리하는 리스트
    private List<Long> itemImgIds = new ArrayList<>();
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    //dto를 가지고 entity를 만든다
    //this dto에 필들값들을 가지고 와서 item이랑 맵핑을 해서 패운다
    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }
    
    //item,ItemFormDto.class -> ItemFormDto에 있느거하고 맞춰서 가지고온다
    //dto를 entity로 변환
    public static ItemFormDto entityToDto(Item item) {
        return modelMapper.map(item,ItemFormDto.class);
    }
}
