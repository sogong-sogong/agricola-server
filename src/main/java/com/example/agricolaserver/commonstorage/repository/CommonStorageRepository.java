package com.example.agricolaserver.commonstorage.repository;

import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonStorageRepository extends JpaRepository<CommonStorage, Long> {
    CommonStorage findByRoom(Room room);
    CommonStorage findByRoomId(Long roomId);
}
