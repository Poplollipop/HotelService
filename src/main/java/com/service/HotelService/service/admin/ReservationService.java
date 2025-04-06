package com.service.HotelService.service.admin;

import com.service.HotelService.dto.RoomStatusResDto;

public interface ReservationService {


    RoomStatusResDto getAllResrvation(int pagesNumber);

    boolean changeReservationsStatus(Long id, String status);
}
