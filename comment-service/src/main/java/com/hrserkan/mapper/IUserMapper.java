package com.hrserkan.mapper;


import com.hrserkan.dto.request.UserProfileUpdateRequestDto;
import com.hrserkan.dto.request.UserSaveRequestDto;
import com.hrserkan.repository.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);
    Comment toUserProfile(UserSaveRequestDto userSaveRequestDto);

    Comment toUserProfile(UserProfileUpdateRequestDto userProfileUpdateRequestDto);

//    UserProfile toUserProfile(RegisterModel registerModel);

//
//    // @Mapping( source = "id",target = "userProfileId")
//    UserProfileFindAllResponseDto toUserProfileFindAllResponseDto(UserProfile userProfile);


}