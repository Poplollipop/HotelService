package com.service.HotelService.service.customer;

import com.service.HotelService.dto.RoomStatusDto;

public interface BookingService {

    boolean post(RoomStatusDto roomStatusDto);
}
