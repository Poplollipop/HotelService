package com.service.HotelService.dto;


import lombok.Data;

@Data
public class RoomsDto {

    private Long id;

    private String name;

    private String type;

    private Long price;

    private boolean available;
}
