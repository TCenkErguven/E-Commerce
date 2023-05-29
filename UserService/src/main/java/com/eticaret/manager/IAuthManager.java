package com.eticaret.manager;

import com.eticaret.dto.request.AuthChangePasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "user-auth",
        url = "http://localhost:8080/api/v1/auth"
)
public interface IAuthManager {
    @PutMapping("/change-password/{password}")
    public ResponseEntity<Boolean> changePassword(@RequestBody AuthChangePasswordRequestDto dto);
}
