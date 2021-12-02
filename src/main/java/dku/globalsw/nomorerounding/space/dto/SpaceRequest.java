package dku.globalsw.nomorerounding.space.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceRequest {

    private String floor;

    private Integer spaceRow;

    private Integer spaceColumn;
}
