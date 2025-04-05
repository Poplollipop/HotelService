package com.service.HotelService.controller.admin;


import com.service.HotelService.dto.RoomsDto;
import com.service.HotelService.service.admin.RoomsService;
import jakarta.persistence.EntityNotFoundException;
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

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(roomsService.getRoomById(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("錯誤發生，請稍後嘗試！");
        }
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id,@RequestBody RoomsDto roomsDto){
        boolean success = roomsService.updateRoom(id, roomsDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
