package com.service.HotelService.controller.admin;


import com.service.HotelService.dto.RoomsDto;
import com.service.HotelService.service.admin.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomsService roomsService;

    @PostMapping("/rooms")
    public ResponseEntity<?> postRoom(@RequestBody RoomsDto roomsDto) {
        boolean success = roomsService.postRooms(roomsDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomsService.getAllRooms(pageNumber));
    }
}
