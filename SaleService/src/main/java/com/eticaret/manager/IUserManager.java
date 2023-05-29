package com.eticaret.manager;

import com.eticaret.dto.response.UserProfileSaleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "sale-user",
        url = "http://localhost:8090/api/v1/user"
)
public interface IUserManager {
    @GetMapping("/get-sale-user/{authId}")
    public ResponseEntity<UserProfileSaleResponseDto> getSaleUser(@PathVariable Long authId);



}
