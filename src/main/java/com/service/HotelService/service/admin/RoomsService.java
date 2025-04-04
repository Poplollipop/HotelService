package com.service.HotelService.service.admin;


import com.service.HotelService.dto.RoomResDto;
import com.service.HotelService.dto.RoomsDto;

public interface RoomsService {

    boolean postRooms(RoomsDto roomsDto);

    RoomResDto getAllRooms(int pageNumber);

    RoomsDto getRoomById(Long id);
}
