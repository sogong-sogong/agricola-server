package com.example.agricolaserver.score.repository;

import com.example.agricolaserver.score.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByMember_Id(Long memberId);
}
