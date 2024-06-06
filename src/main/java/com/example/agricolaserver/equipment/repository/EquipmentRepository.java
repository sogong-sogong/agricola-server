package com.example.agricolaserver.equipment.repository;

import com.example.agricolaserver.equipment.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    List<Equipment> findByMember(Member member);
}