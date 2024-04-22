package intc.spring.shop.controller;

import intc.spring.shop.dto.ItemFormDto;
import intc.spring.shop.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto",new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto,
                          @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList,
                          BindingResult bindingResult, Model model){

    if (bindingResult.hasErrors()){
        return "item/itemForm";
    }

    if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null ){ //이미지를 첨부안했을경우
        model.addAttribute("errorMessage","첫번째 상품 이미지는 필수입니다");
        return "item/itemForm";
    }

        try {
            itemService.saveItem(itemFormDto,itemImgFileList);
        } catch (IOException e) {
            model.addAttribute("errorMessage","상품 등록중 오류가 발생했습니다");
            return "item/itemForm";
        }

        return "redirect:/";
    }

//    @GetMapping("/admin/item/{itemId}")
//    public String itemDetail(@PathVariable("itemId") Long itemId, Model model){
//        ItemFormDto itemFormDto = itemService.getItemDetail(itemId);
//    }
}
