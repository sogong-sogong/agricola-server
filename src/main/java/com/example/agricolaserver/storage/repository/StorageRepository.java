package com.example.agricolaserver.storage.repository;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.storage.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    Storage findByMember(Member member);
}