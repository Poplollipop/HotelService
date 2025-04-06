package com.service.HotelService.service.admin;

import com.service.HotelService.dto.RoomResDto;
import com.service.HotelService.dto.RoomStatusResDto;
import com.service.HotelService.entity.RoomStatus;
import com.service.HotelService.entity.Rooms;
import com.service.HotelService.enums.RoomsStatus;
import com.service.HotelService.repo.RoomStatusRepo;
import com.service.HotelService.repo.RoomsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final RoomStatusRepo roomStatusRepo;

    private final RoomsRepo roomsRepo;

    public static final int SERACH_RESULT_PAGE = 4;

    public RoomStatusResDto getAllResrvation(int pagesNumber) {
        Pageable pageable = PageRequest.of(pagesNumber, SERACH_RESULT_PAGE);

        Page<RoomStatus> roomStatusPage = roomStatusRepo.findAll(pageable);

        RoomStatusResDto roomResDto = new RoomStatusResDto();
        roomResDto.setRoomStatusDto(roomStatusPage.stream().map(RoomStatus::getRoomStatusDto).collect(Collectors.toList()));

        roomResDto.setPagesNumber(roomStatusPage.getPageable().getPageNumber());
        roomResDto.setTotalPages(roomStatusPage.getTotalPages());
        return roomResDto;
    }

    public boolean changeReservationsStatus(Long id, String status) {
        Optional<RoomStatus> optionalRoomStatus = roomStatusRepo.findById(id);
        if (optionalRoomStatus.isPresent()) {
            RoomStatus existedReservation = optionalRoomStatus.get();
            if (Objects.equals(status, "Approve")) {
                existedReservation.setRoomsStatus(RoomsStatus.APPROVED);
            } else {
                existedReservation.setRoomsStatus(RoomsStatus.REJECTED);
            }
            roomStatusRepo.save(existedReservation);
            Rooms existedRooms = existedReservation.getRooms();
            existedRooms.setAvailable(false);
            roomsRepo.save(existedRooms);
            return true;
        }
        return false;
    }

}
