package com.service.HotelService.controller;

import com.service.HotelService.dto.SignReq;
import com.service.HotelService.dto.UserDto;
import com.service.HotelService.service.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignReq signReq) {
        try {
            UserDto createUser = authService.createUser(signReq);
            return new ResponseEntity<>(createUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("使用者已經存在！", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("使用者未建立成功，請稍後再嘗試！", HttpStatus.BAD_REQUEST);
        }
    }
}
