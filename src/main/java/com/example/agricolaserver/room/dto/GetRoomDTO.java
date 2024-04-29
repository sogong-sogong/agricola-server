package com.example.agricolaserver.room.dto;

import com.example.agricolaserver.room.domain.Room;


public record GetRoomDTO(Long roomId, Integer number){
    public static GetRoomDTO makeRoomDTO(Room room) {
        return new GetRoomDTO(room.getId(), room.getNumber());
    }
}