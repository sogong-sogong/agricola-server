package com.example.agricolaserver.commonstorage.controller;

import com.example.agricolaserver.commonstorage.dto.GetCommonStorageResponse;
import com.example.agricolaserver.commonstorage.dto.UpdateCommonStorageRequestDTO;
import com.example.agricolaserver.commonstorage.repository.CommonStorageRepository;
import com.example.agricolaserver.commonstorage.service.CommonStorageService;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.commonstorage.dto.GetCommonStorageDTO;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/commonstorage")
public class CommonStorageController {
    private final CommonStorageRepository commonStorageRepository;
    private final CommonStorageService commonStorageService;

    public CommonStorageController(CommonStorageRepository commonStorageRepository, RoomRepository roomRepository, CommonStorageService commonStorageService) {
        this.commonStorageRepository = commonStorageRepository;
        this.commonStorageService = commonStorageService;
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<GetCommonStorageDTO> getStorageByRoomID(@PathVariable Room roomID) {

        CommonStorage commonstorage = commonStorageRepository.findByRoom(roomID);

        GetCommonStorageDTO commonstorageDTO = GetCommonStorageDTO.makeCommonStorageDTO(commonstorage);

        return ResponseEntity.ok(commonstorageDTO);

    }

    @MessageMapping("/room/common/update")
    @SendTo("/sub/room/{roomId}")
    public GetCommonStorageResponse getCommonStorage(@Payload UpdateCommonStorageRequestDTO requestDTO) {
        Room room = requestDTO.getRoomId();
        return commonStorageService.getCommonStorage(room, requestDTO);
    }
}
