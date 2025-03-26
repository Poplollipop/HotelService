package com.service.HotelService.service;

import com.service.HotelService.dto.SignReq;
import com.service.HotelService.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignReq signReq);
}
