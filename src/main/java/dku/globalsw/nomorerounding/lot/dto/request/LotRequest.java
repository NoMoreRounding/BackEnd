package dku.globalsw.nomorerounding.lot.dto.request;

import dku.globalsw.nomorerounding.space.dto.SpecificSpaceRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotRequest {

    private String floor;

    private Integer lotRow;

    private Integer lotColumn;

    private List<SpecificSpaceRequest> specificSpaceRequests;
}
