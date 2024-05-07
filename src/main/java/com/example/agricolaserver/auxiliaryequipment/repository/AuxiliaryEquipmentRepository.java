package com.example.agricolaserver.auxiliaryequipment.repository;

import com.example.agricolaserver.auxiliaryequipment.domain.AuxiliaryEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuxiliaryEquipmentRepository extends JpaRepository<AuxiliaryEquipment,String> {
}
