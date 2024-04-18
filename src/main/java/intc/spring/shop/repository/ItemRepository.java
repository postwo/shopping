package intc.spring.shop.repository;

import intc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    //실행하는 결과가 list이다
    List<Item> findByItemNm(String itemNm); //검색하면 한개가 나올수도 있고 여러개가 나올수도 있기 때문에 list사용

    //실행하는 결과가 list이다
    //아이템 이름으로 찾거나 또는 아이템 디테일로 찾을거다
    //String itemNm,String itemDetail 입력으로 받은값
    //findBy넣을거Or넣을거
    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);



    List<Item> findByPriceLessThanOrderByPriceDesc(int price);


    //jpql
    //이렇게 하면 Detail이라는 컬럼이 없기 때문에 동작을 안하다 그러므로 이렇게 query를 사용한다
    //entity클래스 이름을 넣어야 한다 변수를 하나 만든다
    //변수로 땡긴다
    //"select i from Item i" 이렇게 하면 컬럼을 다 땡겨오는거다
    //"select i.id,i.itemDetail from Item i" 이런식으로 필요한것만 땡겨올수 있다
    //%:itemDetail% 이렇게 하면 변수로 인식한다
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc ")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail);
}
