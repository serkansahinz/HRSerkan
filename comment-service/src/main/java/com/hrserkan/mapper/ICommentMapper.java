package com.hrserkan.mapper;


import com.hrserkan.repository.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentMapper {
    ICommentMapper INSTANCE= Mappers.getMapper(ICommentMapper.class);
    Comment toUserProfile(UserSaveRequestDto userSaveRequestDto);

    Comment toUserProfile(UserProfileUpdateRequestDto userProfileUpdateRequestDto);

//    UserProfile toUserProfile(RegisterModel registerModel);

//
//    // @Mapping( source = "id",target = "userProfileId")
//    UserProfileFindAllResponseDto toUserProfileFindAllResponseDto(UserProfile userProfile);


}
