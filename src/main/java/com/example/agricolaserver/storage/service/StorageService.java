package com.example.agricolaserver.storage.service;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private final StorageRepository storageRepository;
    private final MemberRepository memberRepository;

    public StorageService(StorageRepository storageRepository, MemberRepository memberRepository) {
        this.storageRepository = storageRepository;
        this.memberRepository = memberRepository;
    }

    public Storage PersonalResources(Member member) {
        //개인의 자원양을 조회
        return storageRepository.findByMember(member);
    }

    @Transactional
    public Storage updateStorage(Long memberId, Integer wood, Integer clay, Integer stone, Integer weed, Integer grain, Integer vegetable, Integer food,
                                 Integer sheep, Integer pig, Integer cow, Integer family, Integer fence, Integer cowshed) {
        Storage storage = storageRepository.findByMemberId(memberId);
        if (storage != null) {
            if (wood != null) storage.setWood(wood);
            if (clay != null) storage.setClay(clay);
            if (stone != null) storage.setStone(stone);
            if (weed != null) storage.setWeed(weed);
            if (grain != null) storage.setGrain(grain);
            if (vegetable != null) storage.setVegetable(vegetable);
            if (food != null) storage.setFood(food);
            if (sheep != null) storage.setSheep(sheep);
            if (pig != null) storage.setPig(pig);
            if (cow != null) storage.setCow(cow);
            if (family != null) storage.setFamily(family);
            if (fence != null) storage.setFence(fence);
            if (cowshed != null) storage.setCowshed(cowshed);
            return storageRepository.save(storage);
        }
        throw new IllegalArgumentException("Storage not found for member id: " + memberId);
    }
}
