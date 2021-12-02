package dku.globalsw.nomorerounding.space.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceResponse {

    private Long userId;

    private Integer spaceRow;

    private Integer spaceColumn;
}
