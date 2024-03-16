package nl.ordina.jtech.wmbus.jokeservice.client;

import nl.ordina.jtech.wmbus.jokeservice.Exception.JokeServiceException;
import nl.ordina.jtech.wmbus.jokeservice.domain.JokeResponse;
import nl.ordina.jtech.wmbus.jokeservice.testutil.TestJokeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"jokeapi.url = jokeapi-url"})
class JokeApiClientTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private JokeApiClient jokeApiClient;

    @Test
    void getJoke() {
        JokeResponse expected = TestJokeFactory.getJokeResponse_simple();
        when(restTemplate.getForObject("jokeapi-url", JokeResponse.class, Map.of("amount", 16))).thenReturn(expected);

        JokeResponse actual = jokeApiClient.getJoke();

        assertEquals(expected, actual);
    }

    @Test
    void getJoke_null() {
        JokeServiceException e = assertThrows(JokeServiceException.class, () -> jokeApiClient.getJoke());

        assertEquals("Type:Null attacked!", e.getMessage());
    }
}