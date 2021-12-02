package dku.globalsw.nomorerounding.auth.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{3,15}",
        message = "아이디는 영문 알파벳, 숫자가 최소 1개 이상 포함해야 하며, 3자 ~ 15자의 길이여야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
        message = "비밀번호는 영문 알파벳, 숫자, 특수기호가 최소 1개 이상 포함해야 하며, 8자 ~ 20자의 길이여야 합니다.")
    private String password;
}
