package nl.ordina.jtech.wmbus.jokeservice;

import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = JokeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JokeServiceApplicationIT {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private String port;

    @Test
    void integrationTest() {
        JokeDto actual = restTemplate.getForObject("http://localhost:" + port + "/joke-service/joke", JokeDto.class);

        assertNotNull(actual);
    }

}
