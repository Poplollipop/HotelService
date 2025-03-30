package com.service.HotelService.dto;


import lombok.Data;

import java.util.List;

@Data
public class RoomResDto {

    private List<RoomsDto> roomsDtoList;

    private Integer pages;

    private Integer pageNumber;

    
}
