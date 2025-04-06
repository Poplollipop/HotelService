package com.service.HotelService.dto;

import com.service.HotelService.enums.RoomsStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomStatusDto {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private RoomsStatus roomsStatus;

    private Long roomId;

    private String roomType;

    private String roomName;

    private Long userId;

    private String userName;

}
