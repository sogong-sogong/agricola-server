package com.example.agricolaserver.equipment.service;

import com.example.agricolaserver.equipment.domain.Equipment;
import com.example.agricolaserver.equipment.dto.EquipmentDTO;
import com.example.agricolaserver.equipment.repository.EquipmentRepository;
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
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    private static final Map<Integer, EquipmentDTO> EQUIPMENT_INFO_MAP = new HashMap<>();
    
    static {
        EQUIPMENT_INFO_MAP.put(1, new EquipmentDTO(1, 1)); // 화로(점토2)
        EQUIPMENT_INFO_MAP.put(2, new EquipmentDTO(2, 1)); // 화로(점토3)
        EQUIPMENT_INFO_MAP.put(3, new EquipmentDTO(3, 1)); // 화덕(점토4)
        EQUIPMENT_INFO_MAP.put(4, new EquipmentDTO(4, 1)); // 화덕(점토5)
        EQUIPMENT_INFO_MAP.put(5, new EquipmentDTO(5, 4)); // 우물
        EQUIPMENT_INFO_MAP.put(6, new EquipmentDTO(6, 2)); // 흙가마
        EQUIPMENT_INFO_MAP.put(7, new EquipmentDTO(7, 3)); // 돌가마
        EQUIPMENT_INFO_MAP.put(8, new EquipmentDTO(8, 2)); // 가구 제작소
        EQUIPMENT_INFO_MAP.put(9, new EquipmentDTO(9, 2)); // 그릇 제작소
        EQUIPMENT_INFO_MAP.put(10, new EquipmentDTO(10, 2)); // 바구니 제작소
    }

    // 각 멤버별로 카드를 분배하는 코드
    // member1: equid 1~2번 카드, member2: equid 3~4번 카드, member3: equid 5~6번 카드, member4: equid 7~8번 카드를 받음.
    public void initCard(Room room, Member member) {
        int startId = (int) ((member.getId() - 1) * 2 + 1);
        int endId = (int) (member.getId() * 2);
        for (int i = startId; i <= endId; i++) {
            EquipmentDTO info = EQUIPMENT_INFO_MAP.get(i);
            if (info != null) {
                int score = info.getScore();
                Equipment equipment = Equipment.builder().id(Integer.toString(info.getEquipmentId())).member(member).room(room).score(score).build();
                equipmentRepository.save(equipment);
            }
        }
    }

    // 조회 메소드 (memberid를 이용해 데이터베이스에서 조회)
    public List<EquipmentDTO> getEquipmentsByMember(Member member) {
        List<Equipment> equipments = equipmentRepository.findByMember(member);
        return equipments.stream()
                .map(this::mapToEquipmentDTO)
                .collect(Collectors.toList());
    }

    // Equipment 객체를 EquipmentDTO 객체로 매핑하는 메소드
    private EquipmentDTO mapToEquipmentDTO(Equipment equipment) {
        return new EquipmentDTO(
            equipment.getMember().getId(),
            equipment.getRoom().getId(),
            Integer.parseInt(equipment.getId()),
            equipment.getScore()
        );
    }

    // 각 멤버별로 Equipment의 score의 총합을 반환하는 메소드
    @Transactional
    public int getEquScoreByMember(Member member) {
        List<Equipment> Equipments = equipmentRepository.findByMember(member);
        return Equipments.stream()
                .mapToInt(Equipment::getScore)
                .sum();
    } 
}