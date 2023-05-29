package com.eticaret.controller;

import com.eticaret.dto.request.ForgotPasswordUserRequestDto;
import com.eticaret.dto.request.GetMyProfileRequestDto;
import com.eticaret.dto.request.UserChangePasswordDto;
import com.eticaret.dto.request.UserUpdateRequestDto;
import com.eticaret.dto.response.GetMyProfileResponseDto;
import com.eticaret.dto.response.UserProfileSaleResponseDto;
import com.eticaret.repository.entity.UserProfile;
import com.eticaret.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.eticaret.constants.EndPointList.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;


    @GetMapping("/find-all")
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @PutMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateUserStatus(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.activateUserStatus(authId));
    }

    @DeleteMapping ("/delete-by-id/{authId}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.deleteUserById(authId));
    }
    @PutMapping("/update")
    public ResponseEntity<UserProfile> update(@RequestBody @Valid UserUpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }
    @CrossOrigin("*")
    @PostMapping("/get-my-profile")
    public ResponseEntity<GetMyProfileResponseDto> getMyProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userProfileService.getMyProfile(dto));
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPasswordUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.forgotPassword(dto));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserChangePasswordDto dto){
        return ResponseEntity.ok(userProfileService.changePassword(dto));
    }

    @GetMapping("/get-sale-user/{authId}")
    public ResponseEntity<UserProfileSaleResponseDto> getSaleUser(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.getSaleUser(authId));
    }

}
