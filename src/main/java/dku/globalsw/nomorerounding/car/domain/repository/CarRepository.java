package dku.globalsw.nomorerounding.car.domain.repository;

import dku.globalsw.nomorerounding.car.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsCarByCarNumber(String carNumber);

}
