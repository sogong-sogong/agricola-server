package com.example.agricolaserver.storage.service;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Storage PersonalResources(Member member) {
        //개인의 자원양을 조회
        return storageRepository.findByMember(member);
    }
}
