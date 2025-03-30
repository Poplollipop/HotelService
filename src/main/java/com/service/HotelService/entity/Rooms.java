package com.service.HotelService.entity;

import com.service.HotelService.dto.RoomsDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long price;

    private boolean available;

    public RoomsDto getRoomsDto(){
        RoomsDto roomsDto = new RoomsDto();
        roomsDto.setId(id);
        roomsDto.setName(name);
        roomsDto.setType(type);
        roomsDto.setAvailable(available);
        roomsDto.setPrice(price);
        return roomsDto;
    }
}
