package com.service.HotelService.service.admin;


import com.service.HotelService.dto.RoomsDto;
import com.service.HotelService.entity.Rooms;
import com.service.HotelService.repo.RoomsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
