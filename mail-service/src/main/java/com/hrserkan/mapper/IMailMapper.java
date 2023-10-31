package com.hrserkan.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


//import com.hrserkan.rabbitmq.model.MailModel;


@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMailMapper {
    IMailMapper INSTANCE= Mappers.getMapper(IMailMapper.class);





//    MailModel toMailModel(Auth auth);


}
