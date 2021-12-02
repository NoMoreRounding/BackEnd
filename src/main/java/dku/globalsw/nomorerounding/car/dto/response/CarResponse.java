package dku.globalsw.nomorerounding.car.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CarResponse {

    private String carNumber;

    private boolean pregnant;

    private boolean compactCar;

    private boolean electric;

    private boolean disabled;
}
