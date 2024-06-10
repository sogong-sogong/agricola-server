package com.example.agricolaserver.auxiliaryequipment.repository;

import com.example.agricolaserver.auxiliaryequipment.domain.AuxiliaryEquipment;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuxiliaryEquipmentRepository extends JpaRepository<AuxiliaryEquipment,String> {
    List<AuxiliaryEquipment> findByMember(Member member);
}
