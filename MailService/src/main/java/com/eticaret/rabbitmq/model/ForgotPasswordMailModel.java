package com.eticaret.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ForgotPasswordMailModel implements Serializable {
    private String password;
    private String email;
    private String username;

}
