package com.service.HotelService.repo;

import com.service.HotelService.entity.Rooms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepo extends JpaRepository<Rooms, Long> {


    Page<Rooms> findByAvailable(boolean available, Pageable pageable);

}
