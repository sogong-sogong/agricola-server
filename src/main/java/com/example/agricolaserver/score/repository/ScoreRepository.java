package com.example.agricolaserver.score.repository;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.score.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByMember(Member member);
}