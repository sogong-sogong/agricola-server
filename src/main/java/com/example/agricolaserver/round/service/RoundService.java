package com.example.agricolaserver.round.service;

import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.round.domain.Round;
import com.example.agricolaserver.round.repository.RoundRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Service
@Transactional
public class RoundService {
    private final RoundRepository roundRepository;
    Random random = new Random();
    public void round1(Room roomId){
        List<com.example.agricolaserver.round.domain.Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("울타리").open(false).build()); //나무 1개 -> 울타리
        cardList.add(Round.builder().roomId(roomId).title("주요 설비").open(false).build()); // 주요 설비나 보조 설비 하나
        cardList.add(Round.builder().roomId(roomId).title("양 시장").open(false).build()); // 양 1개 (매 라운드 +1)
        cardList.add(Round.builder().roomId(roomId).title("곡식 활용").open(false).build()); //씨 뿌리기 그리고/또는 빵 굽기
        int complete = 0;
        while(complete<4){
            int num = random.nextInt(4);
           Round card = cardList.get(num);
            if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
                roundRepository.save(card);
                complete++;
            }
        }
    }
    public void initRound(Room roomId){
        round1(roomId);
    }
}
