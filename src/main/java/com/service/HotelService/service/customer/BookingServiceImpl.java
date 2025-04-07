package com.service.HotelService.service.customer;

import com.service.HotelService.dto.RoomStatusDto;
import com.service.HotelService.dto.RoomStatusResDto;
import com.service.HotelService.entity.RoomStatus;
import com.service.HotelService.entity.Rooms;
import com.service.HotelService.entity.User;
import com.service.HotelService.enums.RoomsStatus;
import com.service.HotelService.repo.RoomStatusRepo;
import com.service.HotelService.repo.RoomsRepo;
import com.service.HotelService.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepo userRepo;

    private final RoomsRepo roomsRepo;

    private final RoomStatusRepo roomStatusRepo;

    public static final int SERACH_RESULT_PAGE = 4;


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
            roomStatusRepo.save(roomStatus);
            return true;
        }
        return false;
    }

    public RoomStatusResDto getAllReservationByUserId(Long userId, int pagesNumber) {
        Pageable pageable = PageRequest.of(pagesNumber, SERACH_RESULT_PAGE);

        Page<RoomStatus> roomStatusPage = roomStatusRepo.findAllByUserId(pageable,userId);

        RoomStatusResDto roomResDto = new RoomStatusResDto();
        roomResDto.setRoomStatusDto(roomStatusPage.stream().map(RoomStatus::getRoomStatusDto).collect(Collectors.toList()));

        roomResDto.setPagesNumber(roomStatusPage.getPageable().getPageNumber());
        roomResDto.setTotalPages(roomStatusPage.getTotalPages());
        return roomResDto;
    }

}
