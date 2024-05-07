package com.example.agricolaserver.family.repository;

import com.example.agricolaserver.family.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {
}