package com.service.HotelService.service;


import com.service.HotelService.dto.SignReq;
import com.service.HotelService.dto.UserDto;
import com.service.HotelService.entity.User;
import com.service.HotelService.enums.UserRole;
import com.service.HotelService.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepo userRepo;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminAccount = userRepo.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@admin.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepo.save(user);
            System.out.println("管理員帳號建立成功！");
        }
        if (!adminAccount.isEmpty()) {
            System.out.println("管理員帳號已存在！");
        }
    }

    public UserDto createUser(SignReq signReq) {
        if (userRepo.findFirstByEmail(signReq.getPassowrd()).isPresent()) {
            throw new EntityExistsException("該信箱已存在其他使用者，請更換其他信箱申請！" + signReq.getEmail());
        }
        User user = new User();
        user.setEmail(signReq.getEmail());
        user.setName(signReq.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signReq.getPassowrd()));
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepo.save(user);
        return createUser.getUserDto();
    }

}
