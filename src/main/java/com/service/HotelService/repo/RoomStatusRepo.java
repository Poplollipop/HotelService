package com.service.HotelService.repo;

import com.service.HotelService.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStatusRepo extends JpaRepository<RoomStatus, Long> {

}
