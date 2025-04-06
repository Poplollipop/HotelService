package com.service.HotelService.service.customer;

import com.service.HotelService.dto.RoomResDto;
import com.service.HotelService.entity.Rooms;
import com.service.HotelService.repo.RoomsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomsRepo roomsRepo;

    public RoomResDto getAvailableRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Rooms> roomsPage = roomsRepo.findByAvailable(true, pageable);
        RoomResDto roomResDto = new RoomResDto();
        roomResDto.setPageNumber(roomsPage.getPageable().getPageNumber());
        roomResDto.setPages(roomsPage.getTotalPages());
        roomResDto.setRoomsDtoList(roomsPage.stream().map(Rooms::getRoomsDto).collect(Collectors.toList()));
        return roomResDto;
    }

}
