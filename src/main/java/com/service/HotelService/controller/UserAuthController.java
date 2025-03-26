package com.service.HotelService.controller;

import com.service.HotelService.dto.AuthenticationReq;
import com.service.HotelService.dto.AuthenticationRes;
import com.service.HotelService.dto.SignReq;
import com.service.HotelService.dto.UserDto;
import com.service.HotelService.entity.User;
import com.service.HotelService.repo.UserRepo;
import com.service.HotelService.service.AuthService;
import com.service.HotelService.utill.JwtUtill;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthService authService;

    private final AuthenticationManager authtManager;

    private final UserRepo userRepo;

    private final JwtUtill jwtUtill;


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

    @PostMapping("/login")
    public AuthenticationRes createAuthenticationToken(@RequestBody AuthenticationReq req) {
        try {
            authtManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("使用者名稱或密碼錯誤！!！");
        } 

        final UserDetails userDetails = null;
        Optional<User> opUser = userRepo.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtill.generateToken(userDetails);
        AuthenticationRes res = new AuthenticationRes();
        if (opUser.isPresent()) {
            res.setJwt(jwt);
            res.setUserRole(opUser.get().getUserRole());
            res.setId(opUser.get().getId());
        }
        return res;
    }

}
