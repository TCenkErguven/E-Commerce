package com.eticaret.controller;

import com.eticaret.dto.request.*;
import com.eticaret.dto.response.LoginResponseDto;
import com.eticaret.dto.response.RegisterResponseDto;
import com.eticaret.exception.AuthManagerException;
import com.eticaret.exception.ErrorType;
import com.eticaret.repository.entity.Auth;
import com.eticaret.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eticaret.constants.EndPointList.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if(dto.getPassword().equals(dto.getRePassword()))
            return ResponseEntity.ok(authService.register(dto));
       throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
    }
    @PutMapping(ACTIVATE_STATUS )
    public ResponseEntity<Boolean> activateStatus(@RequestBody @Valid ActivationRequestDto dto){
            return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<Boolean> deleteById(Long id){
        return ResponseEntity.ok(authService.deleteUserById(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody @Valid ForgotPasswordRequestDto dto){
        return ResponseEntity.ok(authService.forgotPassword(dto));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody AuthChangePasswordRequestDto dto){
        return ResponseEntity.ok(authService.changePassword(dto));
    }

}
