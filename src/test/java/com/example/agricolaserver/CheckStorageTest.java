package com.example.agricolaserver;

import com.example.agricolaserver.commonstorage.dto.GetCommonStorageDTO;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.storage.dto.GetStorageDTO;
import jakarta.transaction.Transactional;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckStorageTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("룸 생성 테스트")
    @Test
    void createRoom(){
        String url = "http://localhost:"+ port +"/room/create";
        ResponseEntity<CreateRoomDTO> response = restTemplate.getForEntity(url,CreateRoomDTO.class);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        AssertionsForClassTypes.assertThat(Objects.requireNonNull(response.getBody()).roomId()).isEqualTo(1L);
    }

    @DisplayName("공동 창고 조회 테스트")
    @Test
    void testGetCommonStorage() {
        Long roomId = 1L;
        String url = "http://localhost:" + port + "/commonstorage/" + roomId;
        ResponseEntity<GetCommonStorageDTO> response = restTemplate.getForEntity(url, GetCommonStorageDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        GetCommonStorageDTO commonStorage = response.getBody();
        assertThat(commonStorage).isNotNull();
        //적절한 필드 값 비교
        assertThat(commonStorage.wood()).isEqualTo(35);
        assertThat(commonStorage.clay()).isEqualTo(29);
        assertThat(commonStorage.stone()).isEqualTo(21);
        assertThat(commonStorage.weed()).isEqualTo(19);
        assertThat(commonStorage.grain()).isEqualTo(27);
        assertThat(commonStorage.vegetable()).isEqualTo(20);
        assertThat(commonStorage.food()).isEqualTo(65);
        assertThat(commonStorage.sheep()).isEqualTo(22);
        assertThat(commonStorage.pig()).isEqualTo(19);
        assertThat(commonStorage.cow()).isEqualTo(17);
    }
}