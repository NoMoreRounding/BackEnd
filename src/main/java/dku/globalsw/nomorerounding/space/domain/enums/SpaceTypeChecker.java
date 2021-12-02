package dku.globalsw.nomorerounding.space.domain.enums;

import dku.globalsw.nomorerounding.car.domain.entity.Car;

public class SpaceTypeChecker {
    public static boolean checker(SpaceType spaceType, Car car) {
        switch (spaceType) {
            case NORMAL:
                return true;
            case COMPACT:
                return car.isCompactCar();
            case DISABLED:
                return car.isDisabled();
            case ELECTRIC:
                return car.isElectric();
            case PREGNANT:
                return car.isPregnant();
            default:
                return false;
        }
    }
}
