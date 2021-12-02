package dku.globalsw.nomorerounding.car.domain.entity;

import dku.globalsw.nomorerounding.base.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
public class Car extends BaseEntity {

    private String carNumber;

    @Column(columnDefinition = "boolean default false")
    private boolean pregnant = false;

    @Column(columnDefinition = "boolean default false")
    private boolean compactCar = false;

    @Column(columnDefinition = "boolean default false")
    private boolean electric = false;

    @Column(columnDefinition = "boolean default false")
    private boolean disabled = false;
}
