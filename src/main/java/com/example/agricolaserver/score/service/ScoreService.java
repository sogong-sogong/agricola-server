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

    public ResponseEntity<ScoreDTO> saveOrUpdateScore(Long memberId, ScoreDTO scoreDTO) {
        logger.info("Saving or updating score for memberId: {}, scoreDTO: {}", memberId, scoreDTO);
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            Optional<Score> scoreOpt = scoreRepository.findByMember_Id(memberId);
            Score score;
            if (scoreOpt.isPresent()) {
                score = scoreOpt.get();
                updateScoreFields(score, scoreDTO);
            } else {
                score = Score.builder()
                        .member(member)
                        .build();
                updateScoreFields(score, scoreDTO);
            }
            scoreRepository.save(score);
            return new ResponseEntity<>(convertToScoreDTO(score), HttpStatus.OK);
        } else {
            logger.warn("Member not found for memberId: {}", memberId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ScoreDTO> getScoreByMemberId(Long memberId) {
        logger.info("Fetching score for memberId: {}", memberId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            Optional<Score> scoreOpt = scoreRepository.findByMember_Id(memberId);
            Score score;
            if (scoreOpt.isPresent()) {
                score = scoreOpt.get();
            } else {
                score = Score.builder()
                        .member(member)
                        .build();
                scoreRepository.save(score);
            }
            return new ResponseEntity<>(convertToScoreDTO(score), HttpStatus.OK);
        } else {
            logger.warn("Member not found for memberId: {}", memberId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ScoreDTO convertToScoreDTO(Score score) {
        return new ScoreDTO(score.getMember().getId(), score.getScore(), score.getField(), score.getCage(), score.getGrain(), score.getVegetable(), score.getSheep(), score.getPig(), score.getCow(), score.getBlank(), score.getFencedCowshed(), score.getMudHouse(), score.getStoneHouse(), score.getFamily(), score.getBegging(), score.getCard(), score.getExtra());
    }    

    private void updateScoreFields(Score score, ScoreDTO scoreDTO) {
        score.setScore(scoreDTO.getScore());
        score.setField(scoreDTO.getField());
        score.setCage(scoreDTO.getCage());
        score.setGrain(scoreDTO.getGrain());
        score.setVegetable(scoreDTO.getVegetable());
        score.setSheep(scoreDTO.getSheep());
        score.setPig(scoreDTO.getPig());
        score.setCow(scoreDTO.getCow());
        score.setBlank(scoreDTO.getBlank());
        score.setFencedCowshed(scoreDTO.getFencedCowshed());
        score.setMudHouse(scoreDTO.getMudHouse());
        score.setStoneHouse(scoreDTO.getStoneHouse());
        score.setFamily(scoreDTO.getFamily());
        score.setBegging(scoreDTO.getBegging());
        score.setCard(scoreDTO.getCard());
        score.setExtra(scoreDTO.getExtra());
    }
}
