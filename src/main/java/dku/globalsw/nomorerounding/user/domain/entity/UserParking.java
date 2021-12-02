package dku.globalsw.nomorerounding.user.domain.entity;

import dku.globalsw.nomorerounding.base.entity.BaseEntity;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserParking extends BaseEntity {

    private Long userId;

    @ElementCollection
    private Map<Integer, Integer> parking;
}
