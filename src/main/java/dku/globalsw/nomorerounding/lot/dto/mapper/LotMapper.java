package dku.globalsw.nomorerounding.lot.dto.mapper;

import dku.globalsw.nomorerounding.base.dto.BaseMapper;
import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import dku.globalsw.nomorerounding.lot.dto.request.LotRequest;
import dku.globalsw.nomorerounding.lot.dto.response.LotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LotMapper extends BaseMapper<LotResponse, Lot> {

    LotMapper INSTANCE = Mappers.getMapper(LotMapper.class);

    @Override
    @Mapping(target = "specificSpaceResponses", ignore = true)
    @Mapping(target = "spaceResponses", ignore = true)
    LotResponse toDto(Lot lot);

    @Mapping(target = "space", ignore = true)
    @Mapping(source = "specificSpaceRequests", target = "specificSpace", ignore = true)
    Lot requestToEntity(LotRequest lotRequest);
}