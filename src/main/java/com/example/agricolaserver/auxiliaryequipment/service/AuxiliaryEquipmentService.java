package com.example.agricolaserver.auxiliaryequipment.service;

import com.example.agricolaserver.auxiliaryequipment.domain.AuxiliaryEquipment;
import com.example.agricolaserver.auxiliaryequipment.repository.AuxiliaryEquipmentRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

@RequiredArgsConstructor
@Transactional
@Service
public class AuxiliaryEquipmentService {
    private final AuxiliaryEquipmentRepository auxiliaryEquipmentRepository;

    private static final Map<Integer, AuxiliaryEquipmentInfo> EQUIPMENT_INFO_MAP = new HashMap<>();
    
    static {
        EQUIPMENT_INFO_MAP.put(1, new AuxiliaryEquipmentInfo(1, 1, 0)); // 침실
        EQUIPMENT_INFO_MAP.put(2, new AuxiliaryEquipmentInfo(2, 0, 0)); // 물통
        EQUIPMENT_INFO_MAP.put(3, new AuxiliaryEquipmentInfo(3, 1, 0)); // 통나무배
        EQUIPMENT_INFO_MAP.put(4, new AuxiliaryEquipmentInfo(4, 0, 0)); // 경질 자기
        EQUIPMENT_INFO_MAP.put(5, new AuxiliaryEquipmentInfo(5, 1, 0)); // 버터 제조기
        EQUIPMENT_INFO_MAP.put(6, new AuxiliaryEquipmentInfo(6, -3, 0)); // 벽난로 선반
        EQUIPMENT_INFO_MAP.put(7, new AuxiliaryEquipmentInfo(7, 0, 0)); // 곡식용 삽
        EQUIPMENT_INFO_MAP.put(8, new AuxiliaryEquipmentInfo(8, 0, 0)); // 포장마차
        EQUIPMENT_INFO_MAP.put(9, new AuxiliaryEquipmentInfo(9, 0, 0)); // 돌 집게
        EQUIPMENT_INFO_MAP.put(10, new AuxiliaryEquipmentInfo(10, 4, 0)); // 병
        EQUIPMENT_INFO_MAP.put(11, new AuxiliaryEquipmentInfo(11, 1, 0)); // 베틀
        EQUIPMENT_INFO_MAP.put(12, new AuxiliaryEquipmentInfo(12, 0, 0)); // 양모 담요
        EQUIPMENT_INFO_MAP.put(13, new AuxiliaryEquipmentInfo(13, 2, 0)); // 네덜란드식 풍차
        EQUIPMENT_INFO_MAP.put(14, new AuxiliaryEquipmentInfo(14, 0, 0)); // 채굴 망치
        EQUIPMENT_INFO_MAP.put(15, new AuxiliaryEquipmentInfo(15, 0, 0)); // 우시장
        EQUIPMENT_INFO_MAP.put(16, new AuxiliaryEquipmentInfo(16, 1, 0)); // 양토 채굴장
        EQUIPMENT_INFO_MAP.put(17, new AuxiliaryEquipmentInfo(17, 0, 0)); // 삼포식 농법
        EQUIPMENT_INFO_MAP.put(18, new AuxiliaryEquipmentInfo(18, 1, 0)); // 타작판
        EQUIPMENT_INFO_MAP.put(19, new AuxiliaryEquipmentInfo(19, 0, 0)); // 여물통
        EQUIPMENT_INFO_MAP.put(20, new AuxiliaryEquipmentInfo(20, 0, 0)); // 작은 우리
        EQUIPMENT_INFO_MAP.put(21, new AuxiliaryEquipmentInfo(21, 0, 0)); // 양치기 지팡이
        EQUIPMENT_INFO_MAP.put(22, new AuxiliaryEquipmentInfo(22, 0, 0)); // 거대 농장
        EQUIPMENT_INFO_MAP.put(23, new AuxiliaryEquipmentInfo(23, 0, 0)); // 대형 온실
        EQUIPMENT_INFO_MAP.put(24, new AuxiliaryEquipmentInfo(24, 0, 0)); // 빵삽
        EQUIPMENT_INFO_MAP.put(25, new AuxiliaryEquipmentInfo(25, 0, 0)); // 노점
        EQUIPMENT_INFO_MAP.put(26, new AuxiliaryEquipmentInfo(26, 1, 0)); // 콩밭
        EQUIPMENT_INFO_MAP.put(27, new AuxiliaryEquipmentInfo(27, 0, 0)); // 폐품 창고
        EQUIPMENT_INFO_MAP.put(28, new AuxiliaryEquipmentInfo(28, 0, 0)); // 올가미 밧줄
    }

    // 각 멤버별로 카드 분배하는 코드
    // member1: auxid 1~7번 카드, member2: auxid 8~14번 카드, member3: auxid 15~21번 카드, member4: auxid 22~28번 카드를 받음.
    public void initCard(Room room, Member member) {
        if (member.getId() == 1) {
            for (int i = 1; i <= 7; i++) {
                AuxiliaryEquipmentInfo info = EQUIPMENT_INFO_MAP.get(i);
                if (info != null) {
                    AuxiliaryEquipment auxiliaryEquipment = AuxiliaryEquipment.builder().id(Integer.toString(info.getAuxId())).member(member).room(room).score(info.getScore()).open(false).build();
                    auxiliaryEquipmentRepository.save(auxiliaryEquipment);
                }
            }
        }
        else if (member.getId() == 2) {
            for (int i = 8; i <= 14; i++) {
                AuxiliaryEquipmentInfo info = EQUIPMENT_INFO_MAP.get(i);
                if (info != null) {
                    AuxiliaryEquipment auxiliaryEquipment = AuxiliaryEquipment.builder().id(Integer.toString(info.getAuxId())).member(member).room(room).score(info.getScore()).open(false).build();
                    auxiliaryEquipmentRepository.save(auxiliaryEquipment);
                }
            }
        }
        else if (member.getId() == 3) {
            for (int i = 15; i <= 21; i++) {
                AuxiliaryEquipmentInfo info = EQUIPMENT_INFO_MAP.get(i);
                if (info != null) {
                    AuxiliaryEquipment auxiliaryEquipment = AuxiliaryEquipment.builder().id(Integer.toString(info.getAuxId())).member(member).room(room).score(info.getScore()).open(false).build();
                    auxiliaryEquipmentRepository.save(auxiliaryEquipment);
                }
            }
        }
        else if (member.getId() == 4) {
            for (int i = 22; i <= 28; i++) {
                AuxiliaryEquipmentInfo info = EQUIPMENT_INFO_MAP.get(i);
                if (info != null) {
                    AuxiliaryEquipment auxiliaryEquipment = AuxiliaryEquipment.builder().id(Integer.toString(info.getAuxId())).member(member).room(room).score(info.getScore()).open(false).build();
                    auxiliaryEquipmentRepository.save(auxiliaryEquipment);
                }
            }
        }
    }
    
    // 보조 설비 조회 메소드 (memberid를 이용해 데이터베이스에서 조회)
    public List<AuxiliaryEquipmentInfo> getAuxiliaryEquipmentsByMemberId(Long memberId) {
        List<AuxiliaryEquipment> auxiliaryEquipments = auxiliaryEquipmentRepository.findAllByMember_Id(memberId);
        return auxiliaryEquipments.stream()
                .map(this::mapToAuxiliaryEquipmentInfo)
                .collect(Collectors.toList());
    }

    // AuxiliaryEquipment 객체를 AuxiliaryEquipmentInfo 객체로 매핑하는 메소드
    private AuxiliaryEquipmentInfo mapToAuxiliaryEquipmentInfo(AuxiliaryEquipment auxiliaryEquipment) {
        return new AuxiliaryEquipmentInfo(
                auxiliaryEquipment.getMember().getId(),
                auxiliaryEquipment.getRoom().getId(),
                Integer.parseInt(auxiliaryEquipment.getId()),
                auxiliaryEquipment.getScore(),
                auxiliaryEquipment.isOpen() ? 1 : 0
        );
    }

    // AuxiliaryEquipmentInfo 클래스 정의 (DTO로 사용)
    @RequiredArgsConstructor
    public static class AuxiliaryEquipmentInfo {
        private final Long memberId;
        private final Long roomId;
        private final Integer auxId;
        private final Integer score;
        private final Integer open;

        public AuxiliaryEquipmentInfo(Integer id, Integer score, Integer open) {
            this.memberId = null; // 임시 값 설정
            this.roomId = null;   // 임시 값 설정
            this.auxId = id;
            this.score = score;
            this.open = open;
        }

        public Long getMemberId() {
            return memberId;
        }

        public Long getRoomId() {
            return roomId;
        }

        public Integer getAuxId() {
            return auxId;
        }

        public Integer getScore() {
            return score;
        }

        public Integer getOpen() {
            return open;
        }
    }
}
