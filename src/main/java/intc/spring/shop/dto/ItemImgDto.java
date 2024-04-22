package intc.spring.shop.dto;

import intc.spring.shop.entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemImgDto {

    private Long id;

    private String imgName; //이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl; //이미지 파일 경로

    private String repImgYn; // 대표이미지 여부

    //프레임워크 사용
    private static ModelMapper modelMapper = new ModelMapper();

    //entity를 dto 로 변경
    public static ItemImgDto entityToDto(ItemImg itemImg){//ItemImg itemImg 이거는 entity이다
        return modelMapper.map(itemImg,ItemImgDto.class);
    }
}
