package com.mike;

import com.mike.DTOs.Record;
import com.mike.repo.RecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "1000")
class HistoryControllerTest {
    @Autowired
    private RecordRepository repository;

    @Autowired
    private WebTestClient testWebClient ;

    @Test
    void getHistoryFor() {
        List<Record> testList = new ArrayList<>();

        testList.add(new Record("Santideva", 10,"1983-05-20 14:20:30"));
        testList.add(new Record("Santideva", 0,"1982-05-20 14:20:40"));
        testList.add(new Record("Santideva", 30,"1982-05-20 14:20:30"));
        testList.add(new Record("Santideva", 20,"1984-05-20 14:20:30"));
        repository.saveAll(testList);
        testWebClient.get()
                .uri("/history/Santideva")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("average").isEqualTo(15.0)
                .jsonPath("allScores.length()").isEqualTo(4)
                .jsonPath("highScore").isEqualTo(30)
                .jsonPath("highScoreDate").isEqualTo("1982-05-20 14:20:30")
                .jsonPath("lowScore").isEqualTo(0)
                .jsonPath("lowScoreDate").isEqualTo("1982-05-20 14:20:40");
        };
    }
