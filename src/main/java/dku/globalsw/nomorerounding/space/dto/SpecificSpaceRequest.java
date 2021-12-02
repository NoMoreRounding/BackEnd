package dku.globalsw.nomorerounding.space.dto;

import dku.globalsw.nomorerounding.space.domain.enums.SpaceType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificSpaceRequest {

    private Integer spaceRow;

    private Integer spaceColumn;

    @Enumerated(EnumType.STRING)
    private SpaceType spaceType = SpaceType.NORMAL;
}
