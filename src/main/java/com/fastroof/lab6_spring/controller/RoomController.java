package com.fastroof.lab6_spring.controller;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.pojo.RoomCreationRequest;
import com.fastroof.lab6_spring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomServiceImpl) {
        this.roomService = roomServiceImpl;
    }

    @GetMapping("/room/new")
    public String createNewRoom(Model model) {
        RoomCreationRequest roomCreationRequest = new RoomCreationRequest();
        model.addAttribute("room", roomCreationRequest);
        return "thymeleaf/room/new";
    }

    @PostMapping("/room/new")
    @ResponseBody
    public Boolean submitNewRoom(@ModelAttribute RoomCreationRequest roomCreationRequest, Principal principal) {
        return roomService.addRoom(roomCreationRequest, principal);
    }

    @GetMapping("/room/edit")
    public String editRoom(Model model, @RequestParam Long id, Principal principal) {
        Room room = roomService.getRoom(id);
        if (room == null) {
             return "redirect:../error";
        } else if (!principal.getName().equals(room.getUser().getEmail())) {
            return "redirect:../error";
        }
        model.addAttribute("room", room);
        return "thymeleaf/room/edit";
    }

    @PostMapping("/room/edit")
    @ResponseBody
    public Boolean submitEditedRoom(@ModelAttribute Room room, @RequestParam Long id, Principal principal) {
        Room oldRoom = roomService.getRoom(id);
        if (oldRoom == null) {
            return false;
        } else if (!principal.getName().equals(oldRoom.getUser().getEmail())) {
            return false;
        }
        return roomService.updateRoom(id, room);
    }

    @GetMapping("/room/delete")
    @ResponseBody
    public Boolean deleteRoom(@RequestParam Long id, Principal principal) {
        Room room = roomService.getRoom(id);
        if (room == null) {
            return false;
        } else if (!principal.getName().equals(room.getUser().getEmail())) {
            return false;
        }
        return roomService.deleteRoom(room);
    }
}
