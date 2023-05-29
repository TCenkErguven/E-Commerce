package com.eticaret.manager;


import com.eticaret.dto.request.ForgotPasswordUserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = "auth-user",
        url = "http://localhost:8090/api/v1/user"
)
public interface IUserManager {
    @PutMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateUserStatus(@PathVariable Long authId);

    @DeleteMapping ("/delete-by-id/{authId}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long authId);

    @PutMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPasswordUserRequestDto dto);

}
