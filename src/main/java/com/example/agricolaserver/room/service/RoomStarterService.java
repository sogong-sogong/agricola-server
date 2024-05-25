package com.example.agricolaserver.room.service;

import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.dto.UpdateStarterRequest;
import com.example.agricolaserver.room.dto.UpdateStarterResponse;
import com.example.agricolaserver.room.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomStarterService {
    private final RoomRepository roomRepository;
    public UpdateStarterResponse updateStarter(Long id, UpdateStarterRequest updateStarterRequest){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.updateStarter(updateStarterRequest.starter());
        return new UpdateStarterResponse(room.getStarter());
    }
}