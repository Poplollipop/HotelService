package com.service.HotelService.service.customer;

import com.service.HotelService.dto.RoomResDto;

public interface RoomService {

    RoomResDto getAvailableRooms(int pageNumber);

}
