package com.example.agricolaserver.cage.repository;

import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CageRepository extends JpaRepository<Cage,Long> {
    List<Cage> findByMember(Member member);
}
