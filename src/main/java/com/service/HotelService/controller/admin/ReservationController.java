package com.service.HotelService.controller.admin;

import com.service.HotelService.service.admin.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/{pagesNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pagesNumber) {
        try {
            return ResponseEntity.ok(reservationService.getAllResrvation(pagesNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("似乎發生錯誤！請稍後再嘗試！");
        }
    }

    @GetMapping("/reservation/{id}/{status}")
    public ResponseEntity<?> changeReservationsStatus(@PathVariable Long id, @PathVariable String status) {
        boolean success = reservationService.changeReservationsStatus(id, status);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("似乎發生錯誤！請稍後再嘗試！");
        }
    }
}
