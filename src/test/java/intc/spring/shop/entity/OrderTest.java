package intc.spring.shop.entity;

import intc.spring.shop.constant.ItemSellStatus;
import intc.spring.shop.dto.MemberFormDto;
import intc.spring.shop.repository.ItemRepository;
import intc.spring.shop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;



    @PersistenceContext
    EntityManager em;



}