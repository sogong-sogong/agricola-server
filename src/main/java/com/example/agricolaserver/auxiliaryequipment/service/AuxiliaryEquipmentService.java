package com.example.agricolaserver.auxiliaryequipment.service;

import com.example.agricolaserver.auxiliaryequipment.domain.AuxiliaryEquipment;
import com.example.agricolaserver.auxiliaryequipment.dto.AuxiliaryEquipmentDTO;
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

    private static final Map<Integer, AuxiliaryEquipmentDTO> EQUIPMENT_INFO_MAP = new HashMap<>();
    
    static {
        EQUIPMENT_INFO_MAP.put(1, new AuxiliaryEquipmentDTO(1, 1, 0)); // 침실
        EQUIPMENT_INFO_MAP.put(2, new AuxiliaryEquipmentDTO(2, 0, 0)); // 물통
        EQUIPMENT_INFO_MAP.put(3, new AuxiliaryEquipmentDTO(3, 1, 0)); // 통나무배
        EQUIPMENT_INFO_MAP.put(4, new AuxiliaryEquipmentDTO(4, 0, 0)); // 경질 자기
        EQUIPMENT_INFO_MAP.put(5, new AuxiliaryEquipmentDTO(5, 1, 0)); // 버터 제조기
        EQUIPMENT_INFO_MAP.put(6, new AuxiliaryEquipmentDTO(6, -3, 0)); // 벽난로 선반
        EQUIPMENT_INFO_MAP.put(7, new AuxiliaryEquipmentDTO(7, 0, 0)); // 곡식용 삽
        EQUIPMENT_INFO_MAP.put(8, new AuxiliaryEquipmentDTO(8, 0, 0)); // 포장마차
        EQUIPMENT_INFO_MAP.put(9, new AuxiliaryEquipmentDTO(9, 0, 0)); // 돌 집게
        EQUIPMENT_INFO_MAP.put(10, new AuxiliaryEquipmentDTO(10, 4, 0)); // 병
        EQUIPMENT_INFO_MAP.put(11, new AuxiliaryEquipmentDTO(11, 1, 0)); // 베틀
        EQUIPMENT_INFO_MAP.put(12, new AuxiliaryEquipmentDTO(12, 0, 0)); // 양모 담요
        EQUIPMENT_INFO_MAP.put(13, new AuxiliaryEquipmentDTO(13, 2, 0)); // 네덜란드식 풍차
        EQUIPMENT_INFO_MAP.put(14, new AuxiliaryEquipmentDTO(14, 0, 0)); // 채굴 망치
        EQUIPMENT_INFO_MAP.put(15, new AuxiliaryEquipmentDTO(15, 0, 0)); // 우시장
        EQUIPMENT_INFO_MAP.put(16, new AuxiliaryEquipmentDTO(16, 1, 0)); // 양토 채굴장
        EQUIPMENT_INFO_MAP.put(17, new AuxiliaryEquipmentDTO(17, 0, 0)); // 삼포식 농법
        EQUIPMENT_INFO_MAP.put(18, new AuxiliaryEquipmentDTO(18, 1, 0)); // 타작판
        EQUIPMENT_INFO_MAP.put(19, new AuxiliaryEquipmentDTO(19, 0, 0)); // 여물통
        EQUIPMENT_INFO_MAP.put(20, new AuxiliaryEquipmentDTO(20, 0, 0)); // 작은 우리
        EQUIPMENT_INFO_MAP.put(21, new AuxiliaryEquipmentDTO(21, 0, 0)); // 양치기 지팡이
        EQUIPMENT_INFO_MAP.put(22, new AuxiliaryEquipmentDTO(22, 0, 0)); // 거대 농장
        EQUIPMENT_INFO_MAP.put(23, new AuxiliaryEquipmentDTO(23, 0, 0)); // 대형 온실
        EQUIPMENT_INFO_MAP.put(24, new AuxiliaryEquipmentDTO(24, 0, 0)); // 빵삽
        EQUIPMENT_INFO_MAP.put(25, new AuxiliaryEquipmentDTO(25, 0, 0)); // 노점
        EQUIPMENT_INFO_MAP.put(26, new AuxiliaryEquipmentDTO(26, 1, 0)); // 콩밭
        EQUIPMENT_INFO_MAP.put(27, new AuxiliaryEquipmentDTO(27, 0, 0)); // 폐품 창고
        EQUIPMENT_INFO_MAP.put(28, new AuxiliaryEquipmentDTO(28, 0, 0)); // 올가미 밧줄
    }

    // 각 멤버별로 카드를 분배하는 코드
    // member1: auxid 1~7번 카드, member2: auxid 8~14번 카드, member3: auxid 15~21번 카드, member4: auxid 22~28번 카드를 받음.
    public void initCard(Room room, Member member) {
        int startId = (int) ((member.getId() - 1) * 7 + 1);
        int endId = (int) (member.getId() * 7);
        for (int i = startId; i <= endId; i++) {
            AuxiliaryEquipmentDTO info = EQUIPMENT_INFO_MAP.get(i);
            if (info != null) {
                AuxiliaryEquipment auxiliaryEquipment = AuxiliaryEquipment.builder().id(Integer.toString(info.getAuxId())).member(member).room(room).score(info.getScore()).open(false).build();
                auxiliaryEquipmentRepository.save(auxiliaryEquipment);
            }
        }
    }
    
    // 보조 설비 조회 메소드 (memberid를 이용해 데이터베이스에서 조회)
    public List<AuxiliaryEquipmentDTO> getAuxiliaryEquipmentsByMemberId(Long memberId) {
        List<AuxiliaryEquipment> auxiliaryEquipments = auxiliaryEquipmentRepository.findAllByMember_Id(memberId);
        return auxiliaryEquipments.stream()
                .map(this::mapToAuxiliaryEquipmentDTO)
                .collect(Collectors.toList());
    }

    // AuxiliaryEquipment 객체를 AuxiliaryEquipmentDTO 객체로 매핑하는 메소드
    private AuxiliaryEquipmentDTO mapToAuxiliaryEquipmentDTO(AuxiliaryEquipment auxiliaryEquipment) {
        return new AuxiliaryEquipmentDTO(
                auxiliaryEquipment.getMember().getId(),
                auxiliaryEquipment.getRoom().getId(),
                Integer.parseInt(auxiliaryEquipment.getId()),
                auxiliaryEquipment.getScore(),
                auxiliaryEquipment.isOpen() ? 1 : 0
        );
    }
}
