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
        cardList.add(Round.builder().roomId(roomId).title("울타리").build());
        cardList.add(Round.builder().roomId(roomId).title("주요 설비").build());
        cardList.add(Round.builder().roomId(roomId).title("양 시장").build());
        cardList.add(Round.builder().roomId(roomId).title("곡식 활용").build());
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
