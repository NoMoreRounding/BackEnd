package dku.globalsw.nomorerounding.car.service;

import dku.globalsw.nomorerounding.car.domain.entity.Car;
import dku.globalsw.nomorerounding.car.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    public boolean isSavedCar(String carNumber) {
        if (carRepository.existsCarByCarNumber(carNumber)) {
            return true;
        } else {
            return false;
        }
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }
}
