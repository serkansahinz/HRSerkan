package com.hrserkan.mapper;

import com.hrserkan.dto.request.RegisterRequestDto;
import com.hrserkan.dto.request.UserSaveRequestDto;
import com.hrserkan.dto.response.RegisterResponseDto;
import com.hrserkan.rabbitmq.model.RegisterModel;
import com.hrserkan.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


//import com.hrserkan.rabbitmq.model.MailModel;




@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(RegisterRequestDto registerRequestDto);
    RegisterResponseDto toRegisterResponseDto(Auth auth);

    @Mapping(source = "id", target = "authId")
    UserSaveRequestDto toUserSaveRequestDto(Auth auth);

    @Mapping(source = "id", target = "authId")
    RegisterModel toRegisterModel(Auth auth);

//    MailModel toMailModel(Auth auth);


}
