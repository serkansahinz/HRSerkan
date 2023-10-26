package com.hrserkan.mapper;

import com.hrserkan.dto.request.RegisterRequestDto;
import com.hrserkan.dto.response.RegisterResponseDto;
import com.hrserkan.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(RegisterRequestDto registerRequestDto);
    RegisterResponseDto toRegisterResponseDto(Auth auth);
}
