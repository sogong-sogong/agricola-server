package com.example.agricolaserver.house.repository;

import com.example.agricolaserver.house.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
}
