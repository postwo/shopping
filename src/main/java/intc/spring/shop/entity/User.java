package intc.spring.shop.entity;

import intc.spring.shop.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private int age;

    @Column(length = 30)
    private String name;
}
