package com.example.agricolaserver.family.repository;

import com.example.agricolaserver.family.domain.Family;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.storage.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {
    List<Family> findAllByMember(Member member);
}