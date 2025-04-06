package com.service.HotelService.entity;

import com.service.HotelService.dto.RoomStatusDto;
import com.service.HotelService.enums.RoomsStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Data
public class RoomStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;
    @Column(name = "rooms_status")
    private RoomsStatus roomsStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rooms rooms;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public RoomStatusDto getRoomStatusDto() {
        RoomStatusDto roomStatusDto = new RoomStatusDto();
        roomStatusDto.setId(id);
        roomStatusDto.setPrice(price);
        roomStatusDto.setCheckInDate(checkInDate);
        roomStatusDto.setCheckOutDate(checkOutDate);
        roomStatusDto.setRoomsStatus(roomsStatus);
        roomStatusDto.setUserId(user.getId());
        roomStatusDto.setUserName(user.getUsername());
        roomStatusDto.setRoomId(rooms.getId());
        roomStatusDto.setRoomName(rooms.getName());
        roomStatusDto.setRoomType(rooms.getType());

        return roomStatusDto;

    }
}
