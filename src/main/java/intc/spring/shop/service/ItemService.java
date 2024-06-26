package intc.spring.shop.service;

import intc.spring.shop.dto.ItemFormDto;
import intc.spring.shop.entity.Item;
import intc.spring.shop.entity.ItemImg;
import intc.spring.shop.repository.ItemImgREpository;
import intc.spring.shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgREpository itemImgREpository;

    //수정
    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
       //변경 감지(dirty checking) = 자동으로 변경으로 감지해서 저장한다
        item.updateItem(itemFormDto);
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        for (int i=0; i<itemImgFileList.size(); i++){
            itemImgService.updateItemImg(itemImgIds.get(i),itemImgFileList.get(i));
        }
        return item.getId();
    }

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException { //사진을 한꺼번에 다섯개를 받아올꺼기때문에 list로 받는다
        //상품등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for (int i=0; i<itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i==0){ //맨첫번째이면
                itemImg.setRepImgYn("Y"); //대표이미지로 쓸거냐
            }else { //첫번째가 아니면
                itemImg.setRepImgYn("N");
            }
            itemImgService.saveItemImg(itemImg,itemImgFileList.get(i));
        }

        return item.getId();
    }

//수정
//    public ItemFormDto getItemDetail(Long itemId) {
//        List<ItemImg> itemImgList = itemImgREpository.findAll();
//    }
}

