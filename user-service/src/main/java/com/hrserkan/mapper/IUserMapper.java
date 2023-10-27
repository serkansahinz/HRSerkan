package com.hrserkan.mapper;

import com.hrserkan.dto.request.UserSaveRequestDto;
import com.hrserkan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);
    UserProfile toUserProfile(UserSaveRequestDto userSaveRequestDto);
//    UserProfile toUserProfile(RegisterModel model);
//
//
//    UserProfile toUserProfile(UserProfileUpdateRequestDto dto);
//
//    // @Mapping( source = "id",target = "userProfileId")
//    UserProfileFindAllResponseDto toUserProfileFindAllResponseDto(UserProfile userProfile);


}
