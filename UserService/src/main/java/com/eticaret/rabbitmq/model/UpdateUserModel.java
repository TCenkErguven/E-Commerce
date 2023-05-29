package com.eticaret.rabbitmq.model;

import com.eticaret.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserModel implements Serializable {
    private Long authId;
    private String username;
    private String email;
    private EStatus status;
}
