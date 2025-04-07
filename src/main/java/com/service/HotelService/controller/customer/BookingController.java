package com.service.HotelService.controller.customer;

import com.service.HotelService.dto.RoomStatusDto;
import com.service.HotelService.service.customer.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> postBooking(@RequestBody RoomStatusDto roomStatusDto) {
        boolean success = bookingService.post(roomStatusDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{userId}/{pagesNumber}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId, @PathVariable int pagesNumber) {
        try {
            return ResponseEntity.ok(bookingService.getAllReservationByUserId(userId, pagesNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("似乎發生錯誤！請稍後再嘗試！");
        }
    }
}
