package com.eticaret.mapper;

import com.eticaret.dto.request.RegisterRequestDto;
import com.eticaret.dto.response.RegisterResponseDto;
import com.eticaret.rabbitmq.model.ForgotPasswordMailModel;
import com.eticaret.rabbitmq.model.MailUserModel;
import com.eticaret.rabbitmq.model.SaveUserModel;
import com.eticaret.rabbitmq.model.UpdateUserModel;
import com.eticaret.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth fromRegisterRequestDtoToAuth(final RegisterRequestDto dto);
    RegisterResponseDto fromAuthToRegisterResponseDto(final Auth auth);
    @Mapping(source="id",target = "authId")
    SaveUserModel fromAuthToSaveUserModel(final Auth auth);
    @Mapping(source="authId",target = "id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth fromUpdateUserModelToAuth(final UpdateUserModel model,@MappingTarget Auth auth);
    MailUserModel fromAuthToMailUserModel(final Auth auth);
    ForgotPasswordMailModel fromAuthToForgotPasswordMailModel(final Auth auth);

}
