package dku.globalsw.nomorerounding.space.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpaceType {

    PREGNANT("P"), COMPACT("C"), ELECTRIC("E"), DISABLED("D"), NORMAL("N");

    private final String value;
}
