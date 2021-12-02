package dku.globalsw.nomorerounding.lot.dto.response;

import dku.globalsw.nomorerounding.space.dto.SpaceResponse;
import dku.globalsw.nomorerounding.space.dto.SpecificSpaceResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotResponse {

    private String floor;

    private Integer lotRow;

    private Integer lotColumn;

    private List<SpecificSpaceResponse> specificSpaceResponses;

    private List<SpaceResponse> spaceResponses;
}
