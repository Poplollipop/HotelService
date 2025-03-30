package com.service.HotelService.service.admin;


import com.service.HotelService.dto.RoomResDto;
import com.service.HotelService.dto.RoomsDto;
import com.service.HotelService.entity.Rooms;
import com.service.HotelService.repo.RoomsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepo roomsRepo;

    public boolean postRooms(RoomsDto roomsDto) {
        try {
            Rooms rooms = new Rooms();
            rooms.setName(roomsDto.getName());
            rooms.setPrice(roomsDto.getPrice());
            rooms.setType(roomsDto.getType());
            rooms.setAvailable(true);

            roomsRepo.save(rooms);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public RoomResDto getAllRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Rooms> roomsPage = roomsRepo.findAll(pageable);
        RoomResDto roomResDto = new RoomResDto();
        roomResDto.setPageNumber(roomsPage.getPageable().getPageNumber());
        roomResDto.setPages(roomsPage.getTotalPages());
        roomResDto.setRoomsDtoList(roomsPage.stream().map(Rooms::getRoomsDto).collect(Collectors.toList()));
        return roomResDto;
    }

}
