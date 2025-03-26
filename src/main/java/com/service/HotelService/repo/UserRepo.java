package com.service.HotelService.repo;

import com.service.HotelService.entity.User;
import com.service.HotelService.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

 Optional<User> findFirstByEmail(String email);

 Optional<User> findByUserRole(UserRole userRole);
}
