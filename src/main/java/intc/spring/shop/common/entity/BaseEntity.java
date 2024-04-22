package intc.spring.shop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})//엔티티들이 변동이 생겼다 그러면 자동으로 감지
@MappedSuperclass //상속받는 클래스에게 매핑 정보만 제공하는 클래스
@Getter
@Setter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy; //누구에 의해서 만들어 졌는지

    @LastModifiedBy
    private String modifiedBy; //누구에 의해서 수정이 되었는지
}
