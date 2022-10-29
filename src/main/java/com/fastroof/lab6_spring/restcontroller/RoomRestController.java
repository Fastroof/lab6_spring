package com.fastroof.lab6_spring.restcontroller;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.pojo.RoomCreationRequest;
import com.fastroof.lab6_spring.service.RoomService;
import com.fastroof.lab6_spring.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class RoomRestController {
    private final RoomService roomService;
    private final SearchService searchService;

    @Autowired
    public RoomRestController(RoomService roomService, SearchService searchService) {
        this.roomService = roomService;
        this.searchService = searchService;
    }

    @GetMapping("/rooms")
    List<Room> getRooms(@RequestParam(required = false) Double area ,
                        @RequestParam(required = false) Integer bedroomCount,
                        @RequestParam(required = false) Integer price,
                        @Valid @Min(0) @RequestParam(required = false, defaultValue = "0")  Integer page,
                        @Valid @Min(1) @Max(5) @RequestParam(required = false, defaultValue = "3")  Integer size
    ) {
        return searchService.findAllByAreaAndBedroomCountAndPriceWithPagination(area, bedroomCount, price, page, size);
    }

    @PostMapping("/room")
    boolean newRoom(@Valid RoomCreationRequest newRoom, Principal principal) {
        return roomService.addRoom(newRoom, principal);
    }

    @GetMapping("/room/{id}")
    Room getRoom(@PathVariable Long id) {
        Room room = roomService.getRoom(id);
        if (room == null) {
            throw new RoomNotFoundException(id);
        }
        return room;
    }

    @PutMapping("/room/{id}")
    boolean updateRoom(@Valid RoomCreationRequest updatedRoom, @PathVariable Long id, Principal principal) {
        Room oldRoom = roomService.getRoom(id);
        if (oldRoom == null) {
            throw new RoomNotFoundException(id);
        } else if (!principal.getName().equals(oldRoom.getUser().getEmail())) {
            throw new UserNotOwnerException(id);
        }
        return roomService.updateRoom(id, updatedRoom, principal);
    }

    @DeleteMapping("/room/{id}")
    Boolean deleteRoom(@PathVariable Long id, Principal principal) {
        Room room = roomService.getRoom(id);
        if (room == null) {
            throw new RoomNotFoundException(id);
        } else if (!principal.getName().equals(room.getUser().getEmail())) {
            throw new UserNotOwnerException(id);
        }
        return roomService.deleteRoom(room);
    }

}
