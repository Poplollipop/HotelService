package com.service.HotelService.service.customer;

import com.service.HotelService.dto.RoomStatusDto;
import com.service.HotelService.entity.RoomStatus;
import com.service.HotelService.entity.Rooms;
import com.service.HotelService.entity.User;
import com.service.HotelService.enums.RoomsStatus;
import com.service.HotelService.repo.RoomStatusRepo;
import com.service.HotelService.repo.RoomsRepo;
import com.service.HotelService.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final UserRepo userRepo;

    private final RoomsRepo roomsRepo;

    private final RoomStatusRepo roomStatusRepo;

    @Transactional
    public boolean post(RoomStatusDto roomStatusDto) {
        Optional<User> optionalUser = userRepo.findById(roomStatusDto.getUserId());
        Optional<Rooms> optionalRooms = roomsRepo.findById(roomStatusDto.getRoomId());
        if (optionalRooms.isPresent() && optionalUser.isPresent()) {
            RoomStatus roomStatus = new RoomStatus();
            roomStatus.setRooms(optionalRooms.get());
            roomStatus.setUser(optionalUser.get());
            roomStatus.setCheckInDate(roomStatusDto.getCheckInDate());
            roomStatus.setCheckOutDate(roomStatusDto.getCheckOutDate());
            roomStatus.setRoomsStatus(RoomsStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(roomStatusDto.getCheckInDate(), roomStatusDto.getCheckOutDate());
            roomStatus.setPrice(optionalRooms.get().getPrice() * days);

            // 日誌：保存之前
            System.out.println("Saving RoomStatus: " + roomStatus);

            roomStatusRepo.save(roomStatus);

            // 日誌：保存後
            System.out.println("RoomStatus saved successfully: " + roomStatus);

            return true;
        }
        return false;
    }


}
