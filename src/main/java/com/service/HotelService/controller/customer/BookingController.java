package com.service.HotelService.controller.customer;

import com.service.HotelService.dto.RoomStatusDto;
import com.service.HotelService.service.customer.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> postBooking(@RequestBody RoomStatusDto roomStatusDto){
        boolean success = bookingService.post(roomStatusDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
