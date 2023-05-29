package com.eticaret.service;

import com.eticaret.dto.request.*;
import com.eticaret.dto.response.GetMyProfileResponseDto;
import com.eticaret.dto.response.UserProfileSaleResponseDto;
import com.eticaret.exception.ErrorType;
import com.eticaret.exception.UserManagerException;
import com.eticaret.manager.IAuthManager;
import com.eticaret.mapper.IUserProfileMapper;
import com.eticaret.rabbitmq.model.SaveUserModel;
import com.eticaret.rabbitmq.producer.UpdateProducer;
import com.eticaret.repository.IUserProfileRepository;
import com.eticaret.repository.entity.UserProfile;
import com.eticaret.repository.entity.enums.EStatus;
import com.eticaret.utility.JwtTokenProvider;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UpdateProducer updateProducer;
    private final IAuthManager authManager;
    public UserProfileService(IUserProfileRepository userRepository,
                              JwtTokenProvider jwtTokenProvider,
                              UpdateProducer updateProducer,
                              IAuthManager authManager){
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.updateProducer = updateProducer;
        this.authManager = authManager;
    }

    public void save(SaveUserModel saveUserModel){
        try {
            save(IUserProfileMapper.INSTANCE.fromSaveUserModelToUserProfile(saveUserModel));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public boolean activateUserStatus(Long authId){
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(authId);
        System.out.println(authId);
        if(optionalUserProfile.isPresent()){
            optionalUserProfile.get().setStatus(EStatus.ACTIVE);
            update(optionalUserProfile.get());
            return true;
        }
        throw new UserManagerException(ErrorType.USER_NOT_FOUND);
    }

    public boolean deleteUserById(Long authId){
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(authId);
        if(optionalUserProfile.isPresent()){
            optionalUserProfile.get().setStatus(EStatus.DELETED);
            update(optionalUserProfile.get());
            return true;
        }
        throw new UserManagerException(ErrorType.USER_NOT_FOUND);
    }

    public UserProfile updateUser(UserUpdateRequestDto dto){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getJwtToken());
        if(authId.isPresent()){
            Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(authId.get());
            if(optionalUserProfile.isPresent()){
                update(IUserProfileMapper.INSTANCE.fromUserUpdateRequestDtoToUserProfile(dto,optionalUserProfile.get()));
                updateProducer.updateUser(IUserProfileMapper.INSTANCE.fromUserProfileToUpdateUserModel(optionalUserProfile.get()));
                return optionalUserProfile.get();
            }
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        throw new UserManagerException(ErrorType.TOKEN_NOT_CREATED);
    }

    public GetMyProfileResponseDto getMyProfile(GetMyProfileRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if(authId.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(authId.get());
        if(optionalUserProfile.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        return IUserProfileMapper.INSTANCE.fromUserProfileToGetMyProfileResponseDto(optionalUserProfile.get());
    }

    public Boolean forgotPassword(ForgotPasswordUserRequestDto dto) {
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(dto.getAuthId());
        if(optionalUserProfile.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        optionalUserProfile.get().setPassword(dto.getPassword());
        update(optionalUserProfile.get());
        return true;
    }

    //change Password

    public Boolean changePassword(UserChangePasswordDto dto){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if(authId.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(authId.get());
        if(optionalUserProfile.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        if(optionalUserProfile.get().getPassword().equals(dto.getOldPassword())){
            authManager.changePassword(AuthChangePasswordRequestDto.builder()
                            .password(dto.getNewPassword())
                            .authId(authId.get())
                    .build());
            optionalUserProfile.get().setPassword(dto.getNewPassword());
            update(optionalUserProfile.get());
            return true;
        }
        throw new UserManagerException(ErrorType.PASSWORD_ERROR);
    }

    //get SaleUser

    public UserProfileSaleResponseDto getSaleUser(Long authId){
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(authId);
        if(optionalUserProfile.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        return IUserProfileMapper.INSTANCE.fromUserProfileToUserProfileSaleResponseDto(optionalUserProfile.get());
    }

}
