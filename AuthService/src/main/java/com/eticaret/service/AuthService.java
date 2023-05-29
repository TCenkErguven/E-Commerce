package com.eticaret.service;

import com.eticaret.dto.request.*;
import com.eticaret.dto.response.LoginResponseDto;
import com.eticaret.dto.response.RegisterResponseDto;
import com.eticaret.exception.AuthManagerException;
import com.eticaret.exception.ErrorType;
import com.eticaret.manager.IUserManager;
import com.eticaret.mapper.IAuthMapper;
import com.eticaret.rabbitmq.model.UpdateUserModel;
import com.eticaret.rabbitmq.producer.ForgotPasswordMailProducer;
import com.eticaret.rabbitmq.producer.MailUserProducer;
import com.eticaret.rabbitmq.producer.RegisterProducer;
import com.eticaret.repository.IAuthRepository;
import com.eticaret.repository.entity.Auth;
import com.eticaret.repository.entity.enums.EStatus;
import com.eticaret.utility.ActivationCodeGenerator;
import com.eticaret.utility.JwtTokenProvider;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserManager userManager;
    private final RegisterProducer registerProducer;
    private final MailUserProducer mailProducer;
    private final ForgotPasswordMailProducer forgotPasswordMailProducer;
    public AuthService(IAuthRepository authRepository,
                       IUserManager userManager,
                       JwtTokenProvider jwtTokenProvider,
                       RegisterProducer registerProducer,
                       MailUserProducer mailProducer,
                       ForgotPasswordMailProducer forgotPasswordMailProducer){
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager = userManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.registerProducer = registerProducer;
        this.mailProducer = mailProducer;
        this.forgotPasswordMailProducer = forgotPasswordMailProducer;
    }
    public RegisterResponseDto register(RegisterRequestDto dto){
        Auth auth = IAuthMapper.INSTANCE.fromRegisterRequestDtoToAuth(dto);
        auth.setActivationCode(ActivationCodeGenerator.generateCode());
        save(auth);
        System.out.println(IAuthMapper.INSTANCE.fromAuthToMailUserModel(auth));
        mailProducer.sendActivationCode(IAuthMapper.INSTANCE.fromAuthToMailUserModel(auth));
        registerProducer.saveNewUser(IAuthMapper.INSTANCE.fromAuthToSaveUserModel(auth));
        return IAuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }
    public Boolean activateStatus(ActivationRequestDto dto){
        Optional<Auth> optionalAuth = authRepository.findById(dto.getId());
        if(optionalAuth.isPresent()) {
            if (optionalAuth.get().getActivationCode().equals(dto.getActivationCode())) {
                optionalAuth.get().setStatus(EStatus.ACTIVE);
                optionalAuth.get().setUpdatedDate(System.currentTimeMillis());
                update(optionalAuth.get());
                userManager.activateUserStatus(optionalAuth.get().getId());
                return true;
            }
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
        throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
    }

    public LoginResponseDto login(LoginRequestDto dto){
        Optional<Auth> optionalAuth = authRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(optionalAuth.isPresent()){
            if(optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
                return LoginResponseDto.builder()
                        .statusCode(200)
                        .message(
                        jwtTokenProvider.createToken(optionalAuth.get().getId())
                        .orElseThrow(() -> {throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);})
                        )
                        .build();
            }
            return LoginResponseDto.builder()
                    .statusCode(4700)
                    .message("User aktif değildir.")
                    .build();
        }
        return LoginResponseDto.builder()
                .statusCode(4100)
                .message("Kullanıcı adı veya şifre hatalı")
                .build();
    }


    public Boolean deleteUserById(Long id){
        Optional<Auth> optionalAuth = authRepository.findById(id);
        if(optionalAuth.isPresent()){
            if(!optionalAuth.get().getStatus().equals(EStatus.DELETED)){
                optionalAuth.get().setStatus(EStatus.DELETED);
                update(optionalAuth.get());
                userManager.deleteUserById(optionalAuth.get().getId());
                return true;
            }
            throw new AuthManagerException(ErrorType.USER_DELETED);
        }
        throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
    }

    public void updateUserInfo(UpdateUserModel model){
        Optional<Auth> auth = findById(model.getAuthId());
        System.out.println(model);
        if(auth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        update(IAuthMapper.INSTANCE.fromUpdateUserModelToAuth(model,auth.get()));

    }

    //Forgot Password yapılacak metodu
    //User tarafına password eklenecek

    public Boolean forgotPassword(ForgotPasswordRequestDto dto){
        Optional<Auth> optionalAuth = authRepository.findOptionalByEmail(dto.getEmail());
        if(optionalAuth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        if(optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
            String randomPassword = UUID.randomUUID().toString();
            optionalAuth.get().setPassword(randomPassword);
            System.out.println(optionalAuth.get());
            System.out.println(IAuthMapper.INSTANCE.fromAuthToForgotPasswordMailModel(optionalAuth.get()));
            userManager.forgotPassword(ForgotPasswordUserRequestDto.builder().password(randomPassword).authId(optionalAuth.get().getId()).build());
            forgotPasswordMailProducer.forgotPasswordMail(IAuthMapper.INSTANCE.fromAuthToForgotPasswordMailModel(optionalAuth.get()));
            update(optionalAuth.get());
            return true;
        }
            if(optionalAuth.get().getStatus().equals(EStatus.PENDING))
                throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
            //USER DELETED
                throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
    }

    public Boolean changePassword(AuthChangePasswordRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findById(dto.getAuthId());
        if(optionalAuth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        optionalAuth.get().setPassword(dto.getPassword());
        update(optionalAuth.get());
        return true;
    }
}
