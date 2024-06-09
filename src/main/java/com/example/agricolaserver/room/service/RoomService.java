package com.example.agricolaserver.room.service;
import com.example.agricolaserver.auxiliaryequipment.service.AuxiliaryEquipmentService;
import com.example.agricolaserver.boardresources.service.BoardResourcesService;
import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.commonstorage.repository.CommonStorageRepository;
import com.example.agricolaserver.equipment.domain.Equipment;
import com.example.agricolaserver.equipment.service.EquipmentService;
import com.example.agricolaserver.family.domain.Family;
import com.example.agricolaserver.family.repository.FamilyRepository;
import com.example.agricolaserver.house.domain.House;
import com.example.agricolaserver.house.repository.HouseRepository;
import com.example.agricolaserver.farm.domain.Farm;
import com.example.agricolaserver.farm.repository.FarmRepository;
import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.cage.repository.CageRepository;
import com.example.agricolaserver.job.service.JobService;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import com.example.agricolaserver.room.dto.GetRoomDTO;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.round.service.InitRoundService;
import com.example.agricolaserver.score.domain.Score;
import com.example.agricolaserver.score.repository.ScoreRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.agricolaserver.room.dto.GetRoomDTO.makeRoomDTO;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;
    private final InitRoundService initRoundService;
    private final CommonStorageRepository commonStorageRepository;
    private final MemberRepository memberRepository;
    private final StorageRepository storageRepository;
    private final HouseRepository houseRepository;
    private final FarmRepository farmRepository;
    private final CageRepository cageRepository;
    private final FamilyRepository familyRepository;
    private final JobService jobService;
    private final AuxiliaryEquipmentService auxiliaryEquipmentService;
    private final EquipmentService equipmentService;
    private final ScoreRepository scoreRepository;
    private final BoardResourcesService boardResourcesService;

    public ResponseEntity<CreateRoomDTO> createRoom() {
        try {
            Random random = new Random();
            Integer starter = random.nextInt(4) + 1;
            Room room = Room.builder().starter(starter).build();
            roomRepository.save(room); //룸 생성
            initRoundService.initRound(room); //라운드카드 초기화
            CommonStorage common = CommonStorage.builder().roomId(room).build(); //공동창고 초기화
            commonStorageRepository.save(common);
            boardResourcesService.initBoardResources(room); //행동판 자원 초기화
            return new ResponseEntity<>(new CreateRoomDTO(room.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateRoomDTO(null), HttpStatus.BAD_REQUEST);
        }
    } //createRoom 이후 계정 입장 전 다른 사용자가 getAllRoom을 호출해 들어갈 시

    public ResponseEntity<List<GetRoomDTO>> getAllRoom() {
        try {
            List<Room> rooms = roomRepository.findAll();
            List<GetRoomDTO> allRoom = new ArrayList<>();
            for (Room room : rooms) {
                allRoom.add(makeRoomDTO(room));
            }
            return new ResponseEntity<>(allRoom, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public List<EntranceResponse> entrance(Long roomId, EntranceRequest entranceRequest) throws MessageDeliveryException {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty() || optionalRoom.get().getNumber() >= 4 || memberRepository.findById(entranceRequest.memberId()).isEmpty()) {
            throw new MessageDeliveryException("방 번호나 멤버 id를 확인하세요.");
        } else if (memberRepository.findById(entranceRequest.memberId()).get().getRoom() == null) { //멤버의 게임방 속성이 null이면
            Room room = optionalRoom.get();
            room.addNumber();
            roomRepository.save(room);
            Member member = memberRepository.findById(entranceRequest.memberId()).get();
            member.setRoom(room); //멤버의 room,number 설정
            member.setNumber(room.getNumber());
            memberRepository.save(member);
            int food;
            if (Objects.equals(member.getNumber(), room.getStarter())) {
                food = 2;
            } else
                food = 3;
            
            // 멤버 자원 초기화
            Storage storage = Storage.builder().memberId(member).food(food).build();
            storageRepository.save(storage);

            // 집 초기화
            House house1 = House.builder().member(member).type("wood").xy(6).build();
            House house2 = House.builder().member(member).type("wood").xy(11).build();
            houseRepository.saveAll(Arrays.asList(house1, house2));

            // [테스트용] 밭 초기화
            // Farm farm1 = Farm.builder().member(member).type(0).xy(3).crop(0).build();
            // Farm farm2 = Farm.builder().member(member).type(1).xy(4).crop(2).build();
            // farmRepository.saveAll(Arrays.asList(farm1, farm2));

            // // [테스트용] 우리 초기화
            // Cage cage1 = Cage.builder().member(member).type(0).stock(0).xy(8).stock_cnt(5).build();
            // Cage cage2 = Cage.builder().member(member).type(2).stock(1).xy(9).stock_cnt(3).build();
            // Cage cage3 = Cage.builder().member(member).type(2).stock(2).xy(10).stock_cnt(4).build();
            // cageRepository.saveAll(Arrays.asList(cage1, cage2, cage3));

            // 가족 초기화
            Family family1 = Family.builder().member(member).room(room).xy(6).status(true).build();
            Family family2 = Family.builder().member(member).room(room).xy(11).status(true).build();
            familyRepository.saveAll(Arrays.asList(family1, family2));

            // 직업 카드 초기화
            // jobService.initCard(room, member);
            jobService.initScenarioCard(room, member);

            // 보조 설비 카드 초기화
            // auxiliaryEquipmentService.initCard(room, member);
            auxiliaryEquipmentService.initScenarioCard(room, member);

            // [테스트용] 주요 설비 카드 초기화
            // equipmentService.initCard(room, member);
            equipmentService.initScenarioCard(room, member);

            Boolean starter = Objects.equals(room.getStarter(), member.getNumber());
            Score score = Score.builder().member(member).build();
            scoreRepository.save(score);
            return getAllMember(room);
        } else {
            throw new MessageDeliveryException("이미 게임방에 입장한 멤버입니다.");
        }

    }
    public List<EntranceResponse> getAllMember(Room room){
        List<Member> memberList = memberRepository.findAllByRoom(room);
        List<EntranceResponse> entranceResponseList = new ArrayList<>();;
        for(Member member:memberList){
            entranceResponseList.add(new EntranceResponse(member.getId(),member.getNumber(), Objects.equals(room.getStarter(), member.getNumber())));
        }
        return entranceResponseList;
    }
}