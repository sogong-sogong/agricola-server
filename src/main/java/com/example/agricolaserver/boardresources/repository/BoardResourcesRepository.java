package com.example.agricolaserver.boardresources.repository;

import com.example.agricolaserver.boardresources.domain.BoardResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardResourcesRepository extends JpaRepository<BoardResources,Long> {
    BoardResources findByRoomIdAndXy(Long roomId, Integer xy);

    List<BoardResources> findAllByRoomId(Long roomId);
}
