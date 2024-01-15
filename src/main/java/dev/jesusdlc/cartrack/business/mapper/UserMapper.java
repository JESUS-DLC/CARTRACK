package dev.jesusdlc.cartrack.business.mapper;

import dev.jesusdlc.cartrack.domain.dto.request.create.UserRequestDto;
import dev.jesusdlc.cartrack.domain.dto.response.UserResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id",source = "id")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "role",source = "role.name")
    UserResponseDto toUserResponseDto(Usuario usuario);


    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "role",ignore = true)
    @Mapping(target = "enabled",ignore = true)
    @Mapping(target = "password",ignore = true)
    Usuario toUser(UserRequestDto userRequestDto);

    List<UserResponseDto> toUserResponseDto(List<Usuario> users);
}
