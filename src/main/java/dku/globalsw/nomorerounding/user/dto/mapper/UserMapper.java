package dku.globalsw.nomorerounding.user.dto.mapper;

import dku.globalsw.nomorerounding.base.dto.BaseMapper;
import dku.globalsw.nomorerounding.auth.dto.request.SignUpRequest;
import dku.globalsw.nomorerounding.user.domain.entity.User;
import dku.globalsw.nomorerounding.user.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<UserResponse, User> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    @Mapping(target = "tokenResponse", ignore = true)
    @Mapping(target = "carResponse", ignore = true)
    UserResponse toDto(User user);

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "password", ignore = true)
    User requestToEntity(SignUpRequest signUpRequest);
}
