package intc.spring.shop.service;

import intc.spring.shop.entity.ItemImg;
import intc.spring.shop.repository.ItemImgREpository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgREpository itemImgREpository;
    private final FileService fileService;


    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {
       String oriImgName = itemImgFile.getOriginalFilename(); //실제 파일명
       String imgName = "";
       String imgUrl = "";

       if(!StringUtils.isEmpty(oriImgName)){ //isEmpty(oriImgName) 오리지널이름이 비어있는 경우
           imgName = fileService.uploadFile(itemImgLocation,oriImgName,itemImgFile.getBytes());
           imgUrl = "/images/item/" + imgName;
       }

       itemImg.updateItemImg(oriImgName,imgName,imgUrl);//한방에수정
        itemImgREpository.save(itemImg);
    }

}
