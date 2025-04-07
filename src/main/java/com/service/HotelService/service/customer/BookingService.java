package com.service.HotelService.service.customer;

import com.service.HotelService.dto.RoomStatusDto;
import com.service.HotelService.dto.RoomStatusResDto;

public interface BookingService {

    boolean post(RoomStatusDto roomStatusDto);

    RoomStatusResDto getAllReservationByUserId(Long userId, int pagesNumber);
}
