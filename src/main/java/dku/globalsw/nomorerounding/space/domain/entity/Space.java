package dku.globalsw.nomorerounding.space.domain.entity;

import dku.globalsw.nomorerounding.base.entity.BaseEntity;
import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Space extends BaseEntity {

    private Long userId;

    private Integer spaceRow;

    private Integer spaceColumn;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot lot;

}
