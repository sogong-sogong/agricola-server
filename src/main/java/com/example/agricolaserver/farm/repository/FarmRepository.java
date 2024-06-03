package com.example.agricolaserver.farm.repository;

import com.example.agricolaserver.farm.domain.Farm;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> {
    List<Farm> findByMember(Member member);
}
