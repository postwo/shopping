package intc.spring.shop.repository;

import intc.spring.shop.constant.ItemSellStatus;
import intc.spring.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //트랜잭션을 걸면 동작하는 동안에는 살아있고 동작이 끝나면 롤백하므로 남아있는데이터가 다 사라진다
class ItemRepositoryTest {


    @Autowired
    private ItemRepository itemRepository;


    @DisplayName("상품생성 테스트 ")
    @Test
    void createItemTest() {
        //Item item = new Item(); //entity 객체생성 //기본생성자

        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .stockNumber(100)
                .itemDetail("테스트 상품에 대한 상세설명")
                .itemSellStatus(ItemSellStatus.SELL) //판매상태
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build(); //builder 패턴

        System.out.println("==================item :"+ item);
        Item saveditem = itemRepository.save(item);
        System.out.println("==================item :"+ saveditem);


    }

   public void createitemList(){ //그냥 여러개 상품을 만들어서 담아줄려고 사용
       for (int i = 1; i <= 10; i++) {
           Item item = Item.builder()
                   .itemNm("테스트 상품"+ i)
                   .price(10000 +i)
                   .stockNumber(100 +i)
                   .itemDetail("테스트 상품에 대한 상세설명"+i)
                   .itemSellStatus(ItemSellStatus.SELL) //판매상태
                   .regTime(LocalDateTime.now())
                   .updateTime(LocalDateTime.now())
                   .build();

           itemRepository.save(item);
       }

   }


   @DisplayName("상품 이름 검색에 대한 테스트")
    @Test
    void findByItemNmTest() {
         createitemList(); //메서드 호출 10개의리스트를 가지고 옴

       //List<Item> finditem= itemRepository.findByItemNm("테스트 상품1");
       itemRepository.findByItemNm("테스트 상품1").forEach(System.out::println); //4번

//       for (Item item : finditem) { //예전방식 1
//           System.out.println(item);
//       }

       //finditem.forEach((item)-> System.out.println(item)); //지금 foreach방식 2번
       //finditem.forEach(System.out :: println); //3번

    }


    @DisplayName("OR 테스트")
    @Test
    void findByItemNmOrItemDetailTest() {
        createitemList(); //리스트 10개 가져오기

        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품2","테스트 상품에 대한 상세설명8");
        itemList.forEach((item)-> {
            System.out.println(item);
        });
    }


    @DisplayName("orderBy 테스트") //10005 미만의 값을 내림참순으로 가지고온다
    @Test
    void findByPriceLessThanOrderByPriceDescTest() {
     createitemList(); //내용을 먼저 가져오기
        List<Item> pricefinde= itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
               pricefinde.forEach((item) -> {
                    System.out.println(item);
                });
    }


    @DisplayName("jpql 테스트")
    @Test
    void findByDetailTest() {
        createitemList();
        itemRepository.findByDetail("1").forEach(System.out::println);
    }
}