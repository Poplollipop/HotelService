package com.service.HotelService.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomStatusResDto {

    private Integer totalPages;

    private Integer pagesNumber;

    private List<RoomStatusDto> roomStatusDto;
}
