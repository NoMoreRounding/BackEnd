package dku.globalsw.nomorerounding.space.dto;

import dku.globalsw.nomorerounding.base.dto.BaseMapper;
import dku.globalsw.nomorerounding.space.domain.entity.Space;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceMapper extends BaseMapper<SpaceResponse, Space> {

    SpaceMapper INSTANCE = Mappers.getMapper(SpaceMapper.class);

    Space requestToEntity(SpaceRequest spaceRequest, Long userId);
}
