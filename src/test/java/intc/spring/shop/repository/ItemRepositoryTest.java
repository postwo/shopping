package intc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import intc.spring.shop.constant.ItemSellStatus;
import intc.spring.shop.entity.Item;
import intc.spring.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static intc.spring.shop.entity.QItem.item;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional //트랜잭션을 걸면 동작하는 동안에는 살아있고 동작이 끝나면 롤백하므로 남아있는데이터가 다 사라진다
class ItemRepositoryTest {


    @Autowired
    private ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager em;

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

    @DisplayName("native 테스트")
    @Test
    void findByDetailNativeTest() {
        createitemList();
        itemRepository.findByDetailNative("1").forEach(System.out::println);
    }



    @DisplayName("querydsl 테스트")
    @Test
    void querydslTest() {
        createitemList();
        JPAQueryFactory query = new JPAQueryFactory(em);
        QItem qItem = item;

        //ctrl+alt+v 하면 변수 자동생성
        //JPAQuery<Tuple> result = query.select().from().where().orderBy();
        //List<Tuple> fetch =result.fetch();

        //두개다 합친거
        //List<Tuple> result = query.select().from().where().orderBy().fetch();


        List<Item> itemList = query.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))// 셀스테이터스에서 판매상태인 녀석이 있냐
                .where(item.itemDetail.like("%" + "1" + "%")) //내가 갖고 있는것중에 1글자가 들어있는걸 가지고 온다
                .orderBy(item.price.desc())
                .fetch();

        //itemList.forEach(System.out::println); //밑에거하고 같은 뜻임
        itemList.forEach((item)-> System.out.println(item));
    }



    public void createitemList2(){ //그냥 여러개 상품을 만들어서 담아줄려고 사용
        for (int i = 1; i <= 5; i++) {
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

        for (int i = 6; i <= 10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품"+ i)
                    .price(10000 +i)
                    .stockNumber(100 +i)
                    .itemDetail("테스트 상품에 대한 상세설명"+i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT) //판매상태
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }

    }




    //QuerydslPredicateExecutor<Item> 이거 테스트
    @DisplayName("querydsl2 테스트")
    @Test
    void querydslTest2() {
        createitemList2();// 5개는 sell 5개는 sold_out 상태이다

        BooleanBuilder builder = new BooleanBuilder(); //값이 존재하면 이걸쓰고 없으면 사용을 안한다
        String itemDetail = "테스트";
        int price = 10002;
        String itemSellStatus = "SELL";

        QItem item = QItem.item;

        builder.and(item.itemDetail.like("%"+itemDetail+"%"));//1. 10개의 리스트를 뽑고
        builder.and(item.price.gt(price));//10개의 리스트중에 10004보다 큰걸 뽑고 (6개)

        //equals 사용할때 이렇게 Utils를 사용할수도 있다
        if (StringUtils.equals(itemSellStatus,ItemSellStatus.SELL)){ //3.sell하고 같으면  //이두개가 같으면
           builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));//4.sell 로 된 얘들을 뽑을거다  // Sell이랑 같은애들을 뽑아온다
        }

        Pageable pageable = PageRequest.of(0,5);

//        Page<Item> page = itemRepository.findAll(builder,pageable);
//        List<Item> content= page.getContent();
//        content.stream().forEach((list)-> System.out.println(content));

        //위에꺼 3개를 한줄로 요약
        itemRepository.findAll(builder,pageable).getContent().stream().forEach(System.out::println);
    }
}