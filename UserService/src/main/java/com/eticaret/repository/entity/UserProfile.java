package com.eticaret.repository.entity;

import com.eticaret.repository.entity.enums.EGender;
import com.eticaret.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserProfile extends Base{

    @Id
    private String id;
    private Long authId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private Long birthDate;
    private String avatar;
    private String password;
    private String address;
    private String phone;
    private EGender gender;
    @Builder.Default
    private EStatus status = EStatus.PENDING;

}
