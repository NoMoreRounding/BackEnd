package dku.globalsw.nomorerounding.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    FEMALE("F"), MALE("M");

    private final String value;
}