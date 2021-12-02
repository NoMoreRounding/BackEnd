package dku.globalsw.nomorerounding.user.domain.entity;

import dku.globalsw.nomorerounding.base.entity.BaseEntity;
import dku.globalsw.nomorerounding.car.domain.entity.Car;
import dku.globalsw.nomorerounding.user.domain.enums.Authority;
import dku.globalsw.nomorerounding.user.domain.enums.Gender;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@DynamicUpdate
@AllArgsConstructor
public class User extends BaseEntity {

    private String loginId;

    private String password;

    private String name;

    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    @OneToOne
    private Car car;

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.USER;

    @Column(columnDefinition = "varchar(400)")
    private String accessToken;

    @Column(columnDefinition = "varchar(400)")
    private String refreshToken;
}
