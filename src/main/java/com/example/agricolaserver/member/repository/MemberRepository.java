package com.example.agricolaserver.member.repository;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    public List<Member> findAllByRoom(Room room);
}
