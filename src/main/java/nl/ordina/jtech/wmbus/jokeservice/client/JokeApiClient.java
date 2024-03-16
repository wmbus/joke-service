package nl.ordina.jtech.wmbus.jokeservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.ordina.jtech.wmbus.jokeservice.Exception.JokeServiceException;
import nl.ordina.jtech.wmbus.jokeservice.domain.JokeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class JokeApiClient {

    private final RestTemplate restTemplate;

    @Value("${jokeapi.url}")
    private String url;
    @Value("${jokeapi.amount:16}")
    private int amount;

    public JokeResponse getJoke() {
        JokeResponse response;
        try {
            log.info("Reaching out to jokeapi...");

            response = restTemplate.getForObject(url, JokeResponse.class, Map.of("amount", amount));
        } catch (RuntimeException e) {
            log.error("jokeapi cannot be reached.", e);

            throw new JokeServiceException("v2.jokeapi.dev used Self-Destruct!");
        }
        validateJokeResponse(response);

        log.debug("Retrieved {} from jokeapi.", response);
        return response;
    }

    private void validateJokeResponse(JokeResponse jokeResponse) {
        if (jokeResponse == null || jokeResponse.jokes() == null) {
            throw new JokeServiceException("Type:Null attacked!");
        }

        if (jokeResponse.jokes().size() != amount) {
            log.warn("The provided variable [amount] was [{}], yet the returned number of jokes was [{}], which does not match. Was this intended?", amount, jokeResponse.jokes().size());
        }
    }
}
