package dku.globalsw.nomorerounding.space.domain.entity;

import dku.globalsw.nomorerounding.base.entity.BaseEntity;
import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import dku.globalsw.nomorerounding.space.domain.enums.SpaceType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SpecificSpace extends BaseEntity {

    private Integer spaceRow;

    private Integer spaceColumn;

    @Enumerated(EnumType.STRING)
    private SpaceType spaceType = SpaceType.NORMAL;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lot lot;

}
