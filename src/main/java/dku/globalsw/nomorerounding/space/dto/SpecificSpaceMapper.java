package dku.globalsw.nomorerounding.space.dto;

import dku.globalsw.nomorerounding.base.dto.BaseMapper;
import dku.globalsw.nomorerounding.space.domain.entity.SpecificSpace;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecificSpaceMapper extends BaseMapper<SpecificSpaceResponse, SpecificSpace> {

    SpecificSpaceMapper INSTANCE = Mappers.getMapper(SpecificSpaceMapper.class);

    SpecificSpace requestToEntity(SpecificSpaceRequest specificSpaceRequest);

    List<SpecificSpace> requestToEntity(List<SpecificSpaceRequest> specificSpaceRequestList);
}
