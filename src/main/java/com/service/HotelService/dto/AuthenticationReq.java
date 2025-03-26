package com.service.HotelService.dto;

import lombok.Data;

@Data
public class AuthenticationReq {
    
    private String email;
    
    private String password;

}
