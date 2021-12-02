package dku.globalsw.nomorerounding.user.dto.response;

import dku.globalsw.nomorerounding.car.dto.response.CarResponse;
import dku.globalsw.nomorerounding.auth.token.TokenResponse;
import dku.globalsw.nomorerounding.user.domain.enums.Gender;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private CarResponse carResponse;

    private TokenResponse tokenResponse;

    public void setTokenResponse(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
    }

    public void setCarResponse(CarResponse carResponse) {
        this.carResponse = carResponse;
    }
}
