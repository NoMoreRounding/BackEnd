package dku.globalsw.nomorerounding.space.dto;

import dku.globalsw.nomorerounding.space.domain.enums.SpaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SpecificSpaceResponse {

    private Integer spaceRow;

    private Integer spaceColumn;

    private SpaceType spaceType;
}
