package com.example.agricolaserver.score.service;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.score.domain.Score;
import com.example.agricolaserver.score.dto.ScoreDTO;
import com.example.agricolaserver.score.repository.ScoreRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import com.example.agricolaserver.farm.domain.Farm;
import com.example.agricolaserver.farm.repository.FarmRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final StorageRepository storageRepository;
    private final FarmRepository farmRepository;
    private final MemberRepository memberRepository;

    public ScoreService(ScoreRepository scoreRepository, StorageRepository storageRepository, FarmRepository farmRepository, MemberRepository memberRepository) {
        this.scoreRepository = scoreRepository;
        this.storageRepository = storageRepository;
        this.farmRepository = farmRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public ResponseEntity<ScoreDTO> calculateScore(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        
        Score score = scoreRepository.findByMember(member);
        Storage storage = storageRepository.findByMember(member);

        int family_score = 6; // 가족 기본 점수
    
        // 밭 점수 계산 ⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️
        int field = member.getFarm_cnt();
        int farm_score = switch (field) {
            case 0, 1 -> -1;
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            default -> field >= 5 ? 4 : 0;
        };
        score.setFarm(farm_score);
    
        // 울타리 점수 계산
        int cage = score.getCage() != null ? score.getCage() : 0;;
        int cage_score = switch (cage) {
            case 0 -> -1;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            default -> cage >= 4 ? 4 : 0;
        };
        score.setCage(cage_score);
    
        // 곡물 점수 계산 ⭐️완료⭐️
        int grain = storage.getGrain();
        int grain_score = switch (grain) {
            case 0 -> -1;
            case 1, 2, 3 -> 1;
            case 4, 5 -> 2;
            case 6, 7 -> 3;
            default -> grain >= 8 ? 4 : 0;
        };
        score.setGrain(grain_score);
    

        // 채소 점수 계산 ⭐️완료⭐️
        int vegetable = storage.getVegetable();
        int vegetable_score = switch (vegetable) {
            case 0 -> -1;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            default -> vegetable >= 4 ? 4 : 0;
        };
        score.setVegetable(vegetable_score);
    
        // 양 점수 계산 ⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️
        int sheep = member.getSheep_cnt();
        int sheep_score = switch (sheep) {
            case 0 -> -1;
            case 1, 2, 3 -> 1;
            case 4, 5 -> 2;
            case 6, 7 -> 3;
            default -> sheep >= 8 ? 4 : 0;
        };
        score.setSheep(sheep_score);
    
        // 돼지 점수 계산 ⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️
        int pig = member.getPig_cnt();
        int pig_score = switch (pig) {
            case 0 -> -1;
            case 1, 2 -> 1;
            case 3, 4 -> 2;
            case 5, 6 -> 3;
            default -> pig >= 7 ? 4 : 0;
        };
        score.setPig(pig_score);
    
        // 소 점수 계산 ⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️
        int cow = member.getCow_cnt();
        int cow_score = switch (cow) {
            case 0 -> -1;
            case 1 -> 1;
            case 2, 3 -> 2;
            case 4, 5 -> 3;
            default -> cow >= 6 ? 4 : 0;
        };
        score.setCow(cow_score);
    
        // 집 점수 계산 ⭐️완료⭐️
        score.setMudHouse(storage.getClay()); // 진흙 집
        score.setStoneHouse(storage.getStone() * 2); // 돌 집
    
        // 빈칸 점수 계산
        int blank = 15 - member.getFarm_cnt() - score.getCage() - score.getFencedCowshed();
        score.setBlank(blank * -1);
    
        // 최종 점수 설정
        score.setScore(score.getFarm() + score.getCage() + score.getGrain() + score.getVegetable() + score.getSheep() + score.getPig() + score.getCow() +score.getMudHouse() + score.getStoneHouse() + score.getBlank() + family_score);

        score = scoreRepository.save(score);
        // Score를 ScoreDTO로 변환하여 반환
        ScoreDTO scoreDTO = convertToScoreDTO(score);
            
        return new ResponseEntity<>(scoreDTO, HttpStatus.OK);
    }

    private ScoreDTO convertToScoreDTO(Score score) {
        return new ScoreDTO(score.getMember().getId(), score.getScore(), score.getFarm(), score.getCage(), score.getGrain(), score.getVegetable(), score.getSheep(), score.getPig(), score.getCow(), score.getBlank(), score.getFencedCowshed(), score.getMudHouse(), score.getStoneHouse(), score.getFamily(), score.getBegging(), score.getCard(), score.getExtra());
    }
}