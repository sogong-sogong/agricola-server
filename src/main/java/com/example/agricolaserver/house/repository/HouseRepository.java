package com.example.agricolaserver.house.repository;

import com.example.agricolaserver.house.domain.House;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
    List<House> findByMember(Member member);
}
