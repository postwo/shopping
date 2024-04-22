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
public class ItemImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_img_id")
    private Long id;

    private String imgName; //이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl; //이미지 파일 경로

    private String repImgYn; // 대표이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //setter 하나씩 수정 하기때문에
    //한꺼번에 수정하고 싶을수도 있기때문에
    public void updateItemImg(String imgName,String oriImgName,String imgUrl){
        this.imgName =imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }



}
