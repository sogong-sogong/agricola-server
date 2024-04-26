package com.example.agricolaserver.common.repository;

import com.example.agricolaserver.common.domain.Common;
import com.example.agricolaserver.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonRepository extends JpaRepository<Common, Room> {
}
