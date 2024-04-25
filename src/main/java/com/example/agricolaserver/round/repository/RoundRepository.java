package com.example.agricolaserver.round.repository;

import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.round.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round,Long> {
    Round findByRoomAndTitle(Room room, String title);
    Round findByTitle(String title);
}
