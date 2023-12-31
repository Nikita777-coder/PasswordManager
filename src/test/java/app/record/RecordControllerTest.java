package app.record;

import app.record.entities.RecordEntity;
import app.record.repositories.RecordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecordControllerTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private RecordRepository repository;
//
//    private void fillRepositoryWith6CorrectRecords() {
//        for (int i = 0; i < 6; ++i) {
//            RecordEntity entity = new RecordEntity();
//            entity.setName("efef" + i);
//            entity.setPassword(String.valueOf(i));
//            repository.save(entity);
//        }
//    }
//
//    @AfterEach
//    public void resetDB() {
//        repository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("post; create correct record (id=\"String.getBytes()\", name=\"sljdksl\", password=\"dfljjlf\"); " +
//            "must return 201 status code with random message due to random strategy generation of UUID")
//    void createRecord() {
//        RecordEntity entity = new RecordEntity();
//        entity.setId(UUID.randomUUID());
//        entity.setName("sljdksl");
//        entity.setPassword("dfljjlf");
//
//        ResponseEntity<String> response = restTemplate.postForEntity("/records", entity, String.class);
//
//        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
//    }
//
//    @Test
//    @DisplayName("post; create empty record; must return 400 status code with message name and password of record can't be empty!")
//    void createRecordWithEmptyEntity() {
//        RecordEntity entity = new RecordEntity();
//        ResponseEntity<String> response = restTemplate.postForEntity("/records", entity, String.class);
//
//        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
//        assertThat(response.getBody(), is("{\"password\":\"password of record can't be " +
//                "empty!\",\"name\":\"name of record can't be empty!\"}"));
//    }
//
//    @Test
//    @DisplayName("post; create entity with password=\"dfdf\" record; must return 400 status code with message " +
//            "\"name of record can't be empty!\"")
//    void createRecordWithEmptyName() {
//        RecordEntity entity = new RecordEntity();
//        entity.setPassword("dfdf");
//        ResponseEntity<String> response = restTemplate.postForEntity("/records", entity, String.class);
//
//        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
//        assertThat(response.getBody(), is("{\"name\":\"name of record can't be empty!\"}"));
//    }
//
//    @Test
//    @DisplayName("post; create entity with name=\"efkj\" record; must return 400 status code with message " +
//            "\"password of record can't be empty!\"")
//    void createRecordWithEmptyPassword() {
//        RecordEntity entity = new RecordEntity();
//        entity.setName("efkj");
//        ResponseEntity<String> response = restTemplate.postForEntity("/records", entity, String.class);
//
//        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
//        assertThat(response.getBody(), is("{\"password\":\"password of record can't be empty!\"}"));
//    }
//
//    @Test
//    @DisplayName("get; create 6 correct entities; must return 200 status code with 6 values")
//    void getRecords() {
//        fillRepositoryWith6CorrectRecords();
//        ResponseEntity<List> response = restTemplate.getForEntity("/records", List.class);
//
//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
//        assertThat(Objects.requireNonNull(response.getBody()).size(), is(6));
//    }
//
//    @Test
//    @DisplayName("get by id; create record with fixed id and name=\"dfdfd\"; must return " +
//            "200 status code fixed id and name=\"dfdfd\"")
//    void getRecordById() {
//        UUID id = UUID.randomUUID();
//        RecordEntity entity = new RecordEntity();
//        entity.setName("dfdfd");
//        entity.setPassword("dfdf");
//        entity.setId(id);
//
//        repository.save(entity);
//        ResponseEntity<RecordEntity> response = restTemplate.getForEntity("/records/" + id, RecordEntity.class);
//
//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
//        assertThat(Objects.requireNonNull(response.getBody()).getId(), is(id));
//    }
//
//    @Test
//    void updateRecordById() {
//    }
}