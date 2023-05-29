package com.eticaret.dto.request;

import com.eticaret.repository.entity.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    private String jwtToken;
    private String username;
    private String email;
    private String name;
    private String surname;
    private Long birthDate;
    private String avatar;
    private String address;
    private String phone;
    private EGender gender;

}
