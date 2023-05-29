package com.eticaret.mapper;

import com.eticaret.dto.request.UserUpdateRequestDto;
import com.eticaret.dto.response.GetMyProfileResponseDto;
import com.eticaret.dto.response.UserProfileSaleResponseDto;
import com.eticaret.rabbitmq.model.SaveUserModel;
import com.eticaret.rabbitmq.model.UpdateUserModel;
import com.eticaret.repository.entity.UserProfile;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);
    UserProfile fromSaveUserModelToUserProfile(final SaveUserModel model);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfile fromUserUpdateRequestDtoToUserProfile(UserUpdateRequestDto dto, @MappingTarget UserProfile userProfile);
    UpdateUserModel fromUserProfileToUpdateUserModel(final UserProfile userProfile);
    GetMyProfileResponseDto fromUserProfileToGetMyProfileResponseDto(final UserProfile userProfile);
    UserProfileSaleResponseDto fromUserProfileToUserProfileSaleResponseDto(final UserProfile userProfile);

}
