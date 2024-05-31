// package com.example.agricolaserver;

// import com.example.agricolaserver.member.dto.CreateMemberDTO;
// import com.example.agricolaserver.storage.domain.Storage;
// import com.example.agricolaserver.storage.repository.StorageRepository;
// import org.assertj.core.api.AssertionsForClassTypes;
// import org.junit.After;
// import org.junit.Test;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.test.web.server.LocalServerPort;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit4.SpringRunner;

// import java.util.List;
// import java.util.Objects;

// import static org.assertj.core.api.Assertions.assertThat;

// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class StorageTest {

//     @LocalServerPort
//     private int port;

//     @Autowired
//     private TestRestTemplate restTemplate;

//     @Autowired
//     private StorageRepository storageRepository;

//     @After
//     public void tearDown() throws Exception {
//         storageRepository.deleteAll();
//     }

//     @DisplayName("멤버 생성 테스트")
//     @org.junit.jupiter.api.Test
//     void createMember(){
//         String url = "http://localhost:"+port+"/member/create";
//         ResponseEntity<CreateMemberDTO> response = restTemplate.getForEntity(url,CreateMemberDTO.class);
//         AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//         AssertionsForClassTypes.assertThat(Objects.requireNonNull(response.getBody()).memberId()).isEqualTo(1L);
//     }

//     @DisplayName("개인 창고 업데이트")
//     @Test
//     public void storage_업데이트() throws Exception {
//         // Given
//         Integer expectedWood = 5;
//         Integer expectedClay = 7;

//         Storage saveStorage = storageRepository.save(Storage.builder().memberId().wood(0).clay(0).build());
//         Long updateId = saveStorage.getId();

//         StorageUpdateRequestDTO updateRequestDTO = StorageUpdateRequestDTO.builder()
//                 .wood(expectedWood)
//                 .clay(expectedClay)
//                 .build();

//         String url = "http://localhost:" + port + "/storage/update/" + updateId;

//         HttpEntity<StorageUpdateRequestDTO> requestEntity = new HttpEntity<>(updateRequestDTO);

//         // When
//         ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

//         // Then
//         assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//         assertThat(responseEntity.getBody()).isGreaterThan(0L);

//         List<Storage> all = storageRepository.findAll();
//         assertThat(all.get(0).getWood()).isEqualTo(expectedWood);
//         assertThat(all.get(0).getClay()).isEqualTo(expectedClay);
//     }
// }