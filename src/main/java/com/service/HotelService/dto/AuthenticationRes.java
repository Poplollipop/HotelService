package com.service.HotelService.dto;

import com.service.HotelService.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationRes {
    
    private String jwt;

    private Long id;

    private UserRole userRole;
    
}
