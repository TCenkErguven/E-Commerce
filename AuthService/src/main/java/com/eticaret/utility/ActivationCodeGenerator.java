package com.eticaret.utility;

import lombok.Data;

import java.util.Arrays;
import java.util.UUID;

@Data
public class ActivationCodeGenerator {

    public static String generateCode(){
        String code = UUID.randomUUID().toString();
        String[] data = code.split("-");
        String newCode = "";
        for(String item : data){
            newCode += item.charAt(0);
        }
        return newCode;
    }

}
