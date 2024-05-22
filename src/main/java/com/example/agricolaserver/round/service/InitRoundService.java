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
public class InitRoundService {
    private final RoundRepository roundRepository;
    Random random = new Random();
    public void round1(Room roomId){
        List<Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("울타리").open(false).build()); //나무 1개 -> 울타리
        cardList.add(Round.builder().roomId(roomId).title("주요 설비").open(false).build()); // 주요 설비나 보조 설비 하나
        cardList.add(Round.builder().roomId(roomId).title("양 시장").open(false).build()); // 양 1개 (매 라운드 +1)
        cardList.add(Round.builder().roomId(roomId).title("곡식 활용").open(false).build()); //씨 뿌리기 그리고/또는 빵 굽기
        int complete = 0;
        while(complete<4){
            int num = random.nextInt(4);
            Round card = cardList.get(num);
            if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
                card.setOrderNumber(complete+1);
                roundRepository.save(card);
                complete++;
            }
        }
    }
    public void round2(Room roomId){
        List<Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("기본 가족 늘리기").open(false).build()); //가족 늘리기 한 후에 보조 설비 하나
        cardList.add(Round.builder().roomId(roomId).title("서부 채석장").open(false).build()); // 돌 1개 (매 라운드 +1)
        cardList.add(Round.builder().roomId(roomId).title("집 개조").open(false).build()); // 집 고치기 한 후에 주요 설비나 보조 설비 하나
        int complete = 0;
        while(complete<3){
            int num = random.nextInt(3);
            Round card = cardList.get(num);
            if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
                card.setOrderNumber(complete+5);
                roundRepository.save(card);
                complete++;
            }
        }
    }

    public void round3(Room roomId){
        List<Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("돼지 시장").open(false).build()); //돼지 1개 (매 라운드 +1)
        cardList.add(Round.builder().roomId(roomId).title("채소 종자").open(false).build()); // 채소 1개
        int complete = 0;
        while(complete<2){
            int num = random.nextInt(2);
            Round card = cardList.get(num);
            if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
                card.setOrderNumber(complete+8);
                roundRepository.save(card);
                complete++;
            }
        }
    }

    public void round4(Room roomId){
        List<Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("소 시장").open(false).build()); // 소 1개 (매 라운드 +1)
        cardList.add(Round.builder().roomId(roomId).title("동부 채석장").open(false).build()); // 돌 1개 (매 라운드 +1)
        int complete = 0;
        while(complete<2){
            int num = random.nextInt(2);
            Round card = cardList.get(num);
            if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
                card.setOrderNumber(complete+10);
                roundRepository.save(card);
                complete++;
            }
        }
    }

    public void round5(Room roomId){
        List<Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("급한 가족 늘리기").open(false).build()); // 빈방 없이 가족 늘리기
        cardList.add(Round.builder().roomId(roomId).title("밭 농사").open(false).build()); // 밭 하나 일구기 그리고/또는 씨 뿌리기
        int complete = 0;
        while(complete<2){
            int num = random.nextInt(2);
            Round card = cardList.get(num);
            if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
                card.setOrderNumber(complete+12);
                roundRepository.save(card);
                complete++;
            }
        }
    }

    public void round6(Room roomId){
        List<Round> cardList = new ArrayList<>();
        cardList.add(Round.builder().roomId(roomId).title("농장 개조").open(false).build()); //집 고치기 한 후에 울타리 치기

        Round card = cardList.get(0);

        if(roundRepository.findByRoomAndTitle(roomId,card.getTitle()) == null ){
            card.setOrderNumber(14);
            roundRepository.save(card);
        }
    }

    public void initRound(Room roomId){
        round1(roomId);
        round2(roomId);
        round3(roomId);
        round4(roomId);
        round5(roomId);
        round6(roomId);
    }
}