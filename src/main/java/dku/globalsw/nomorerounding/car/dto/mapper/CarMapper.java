package dku.globalsw.nomorerounding.car.dto.mapper;

import dku.globalsw.nomorerounding.base.dto.BaseMapper;
import dku.globalsw.nomorerounding.car.domain.entity.Car;
import dku.globalsw.nomorerounding.car.dto.response.CarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper extends BaseMapper<CarResponse, Car> {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
}
