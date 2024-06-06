package com.example.agricolaserver.score.service;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.score.domain.Score;
import com.example.agricolaserver.score.dto.ScoreDTO;
import com.example.agricolaserver.score.repository.ScoreRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import com.example.agricolaserver.farm.service.FarmService;
import com.example.agricolaserver.cage.service.CageService;
import com.example.agricolaserver.house.service.HouseService;
import com.example.agricolaserver.auxiliaryequipment.service.AuxiliaryEquipmentService;
// import com.example.agricolaserver.equipment.service.EquipmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final StorageRepository storageRepository;
    private final FarmService farmService;
    private final CageService cageService;
    private final HouseService houseService;
    private final AuxiliaryEquipmentService auxiliaryEquipmentService;
    private final MemberRepository memberRepository;

    public ScoreService(ScoreRepository scoreRepository,
                        StorageRepository storageRepository,
                        FarmService farmService,
                        CageService cageService,
                        HouseService houseService,
                        AuxiliaryEquipmentService auxiliaryEquipmentService,
                        MemberRepository memberRepository) {
        this.scoreRepository = scoreRepository;
        this.storageRepository = storageRepository;
        this.farmService = farmService;
        this.cageService = cageService;
        this.houseService = houseService;
        this.auxiliaryEquipmentService = auxiliaryEquipmentService;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public ResponseEntity<ScoreDTO> calculateScore(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        
        Score score = scoreRepository.findByMember(member);
        Storage storage = storageRepository.findByMember(member);

        // 가족 점수 계산
        score.setFamily(storage.getFamily() * 3);
    
        // 밭 점수 계산
        int farm = farmService.countFarmsByMember(member);
        int farm_score = switch (farm) {
            case 0, 1 -> -1;
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            default -> farm >= 5 ? 4 : 0;
        };
        score.setFarm(farm_score);
    
        // 울타리만 있는 우리 점수 계산
        int cage = cageService.countCages0ByMember(member);
        int cage_score = switch (cage) {
            case 0 -> -1;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            default -> cage >= 4 ? 4 : 0;
        };
        score.setCage(cage_score);

        // 울타리+외양간이 있는 우리 점수 계산
        score.setFencedCowshed(cageService.countCages2ByMember(member));
    
        // 곡물 점수 계산
        int grain = storage.getGrain();
        int grain_score = switch (grain) {
            case 0 -> -1;
            case 1, 2, 3 -> 1;
            case 4, 5 -> 2;
            case 6, 7 -> 3;
            default -> grain >= 8 ? 4 : 0;
        };
        score.setGrain(grain_score);
    

        // 채소 점수 계산
        int vegetable = storage.getVegetable();
        int vegetable_score = switch (vegetable) {
            case 0 -> -1;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            default -> vegetable >= 4 ? 4 : 0;
        };
        score.setVegetable(vegetable_score);
    
        // 양 점수 계산
        int sheep = cageService.countSheepByMember(member);
        int sheep_score = switch (sheep) {
            case 0 -> -1;
            case 1, 2, 3 -> 1;
            case 4, 5 -> 2;
            case 6, 7 -> 3;
            default -> sheep >= 8 ? 4 : 0;
        };
        score.setSheep(sheep_score);
    
        // 돼지 점수 계산
        int pig = cageService.countPigByMember(member);
        int pig_score = switch (pig) {
            case 0 -> -1;
            case 1, 2 -> 1;
            case 3, 4 -> 2;
            case 5, 6 -> 3;
            default -> pig >= 7 ? 4 : 0;
        };
        score.setPig(pig_score);
    
        // 소 점수 계산
        int cow = cageService.countCowByMember(member);
        int cow_score = switch (cow) {
            case 0 -> -1;
            case 1 -> 1;
            case 2, 3 -> 2;
            case 4, 5 -> 3;
            default -> cow >= 6 ? 4 : 0;
        };
        score.setCow(cow_score);
    
        // 집 점수 계산
        score.setMudHouse(houseService.countMudHouseByMember(member)); // 진흙 집
        score.setStoneHouse(houseService.countStoneHouseByMember(member) * 2); // 돌 집
    
        // 빈칸 점수 계산
        int blank = -15
                    + farmService.countFarmsByMember(member)
                    + cageService.countCages0ByMember(member)
                    + cageService.countCages1ByMember(member)
                    + cageService.countCages2ByMember(member)
                    + houseService.countWoodHouseByMember(member)
                    + houseService.countMudHouseByMember(member)
                    + houseService.countStoneHouseByMember(member);
        score.setBlank(blank);
    
        // 카드 점수 계산
        score.setCard(auxiliaryEquipmentService.getAuxScoreByMemberId(member));

        // 최종 점수 설정
        score.setScore(score.getFarm()
                     + score.getCage()
                     + score.getGrain()
                     + score.getVegetable()
                     + score.getSheep()
                     + score.getPig()
                     + score.getCow()
                     + score.getBlank()
                     + score.getFencedCowshed()
                     + score.getMudHouse()
                     + score.getStoneHouse()
                     + score.getFamily()
                     + score.getBegging()
                     + score.getCard()
                     + score.getExtra());

        score = scoreRepository.save(score);

        // Score를 ScoreDTO로 변환하여 반환
        ScoreDTO scoreDTO = convertToScoreDTO(score);
            
        return new ResponseEntity<>(scoreDTO, HttpStatus.OK);
    }

    private ScoreDTO convertToScoreDTO(Score score) {
        return new ScoreDTO(score.getMember().getId(),
                            score.getScore(),
                            score.getFarm(),
                            score.getCage(),
                            score.getGrain(),
                            score.getVegetable(),
                            score.getSheep(),
                            score.getPig(),
                            score.getCow(),
                            score.getBlank(),
                            score.getFencedCowshed(),
                            score.getMudHouse(),
                            score.getStoneHouse(),
                            score.getFamily(),
                            score.getBegging(),
                            score.getCard(),
                            score.getExtra());
    }
}