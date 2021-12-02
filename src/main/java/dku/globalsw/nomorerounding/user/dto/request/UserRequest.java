package dku.globalsw.nomorerounding.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Nullable
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
        message = "비밀번호는 영문 알파벳, 숫자, 특수기호가 최소 1개 이상 포함해야 하며, 8자 ~ 20자의 길이여야 합니다.")
    private String password;

    @Nullable
    private String name;

    @Nullable
    @Email(message = "이메일 형식이 아닙니다")
    private String email;

    @Nullable
    private String carNumber;

    @Nullable
    private Boolean pregnant;

    @Nullable
    private Boolean compactCar;

    @Nullable
    private Boolean electric;

    @Nullable
    private Boolean disabled;
}
