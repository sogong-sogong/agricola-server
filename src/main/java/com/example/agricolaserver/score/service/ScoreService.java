package com.example.agricolaserver.score.service;

import com.example.agricolaserver.score.domain.Score;
import com.example.agricolaserver.score.dto.ScoreDTO;
import com.example.agricolaserver.score.repository.ScoreRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(ScoreService.class);

    public ResponseEntity<ScoreDTO> getScoreByMemberId(Long memberId) {
        logger.info("Fetching score for memberId: {}", memberId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isPresent()) {
            Optional<Score> scoreOpt = scoreRepository.findByMember_Id(memberId);
            if (scoreOpt.isPresent()) {
                Score score = scoreOpt.get();
                return new ResponseEntity<>(convertToScoreDTO(score), HttpStatus.OK);
            } else {
                logger.warn("Score not found for memberId: {}", memberId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            logger.warn("Member not found for memberId: {}", memberId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ScoreDTO convertToScoreDTO(Score score) {
        return new ScoreDTO(score.getMember().getId(), score.getScore(), score.getField(), score.getCage(), score.getGrain(), score.getVegetable(), score.getSheep(), score.getPig(), score.getCow(), score.getBlank(), score.getFencedCowshed(), score.getMudHouse(), score.getStoneHouse(), score.getFamily(), score.getBegging(), score.getCard(), score.getExtra());
    }
}
