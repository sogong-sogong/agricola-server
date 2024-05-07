package com.example.agricolaserver.auxiliaryequipment.service;
import com.example.agricolaserver.auxiliaryequipment.domain.AuxiliaryEquipment;
import com.example.agricolaserver.auxiliaryequipment.repository.AuxiliaryEquipmentRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class AuxiliaryEquipmentService {
    private final AuxiliaryEquipmentRepository auxiliaryEquipmentRepository;
    public void initCard(Room room, Member member){
        if(member.getNumber()==1){
            AuxiliaryEquipment auxiliaryEquipment = AuxiliaryEquipment.builder().id("물통").member(member).room(room).build();
            auxiliaryEquipmentRepository.save(auxiliaryEquipment);
        }
    }
}