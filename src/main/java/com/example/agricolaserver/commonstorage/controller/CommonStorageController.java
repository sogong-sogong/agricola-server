package com.example.agricolaserver.commonstorage.controller;

import com.example.agricolaserver.commonstorage.repository.CommonStorageRepository;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.commonstorage.dto.GetCommonStorageDTO;
import com.example.agricolaserver.storage.dto.GetStorageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/commonstorage")
public class CommonStorageController {
    private final CommonStorageRepository commonStorageRepository;

    public CommonStorageController(CommonStorageRepository commonStorageRepository, RoomRepository roomRepository) {
        this.commonStorageRepository = commonStorageRepository;
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<GetCommonStorageDTO> getStorageByRoomID(@PathVariable Room roomID) {


        CommonStorage commonstorage = commonStorageRepository.findByRoom(roomID);

        GetCommonStorageDTO commonstorageDTO = GetCommonStorageDTO.makeCommonStorageDTO(commonstorage);

        return ResponseEntity.ok(commonstorageDTO);

    }
}
