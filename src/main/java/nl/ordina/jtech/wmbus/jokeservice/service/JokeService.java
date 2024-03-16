package nl.ordina.jtech.wmbus.jokeservice.service;

import lombok.RequiredArgsConstructor;
import nl.ordina.jtech.wmbus.jokeservice.Exception.JokeNotFoundException;
import nl.ordina.jtech.wmbus.jokeservice.client.JokeApiClient;
import nl.ordina.jtech.wmbus.jokeservice.domain.Joke;
import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import nl.ordina.jtech.wmbus.jokeservice.mapper.JokeMapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class JokeService {

    private final JokeMapper jokeMapper;
    private final JokeApiClient jokeApiClient;

    public JokeDto getShortestJoke() {
        return jokeApiClient.getJoke().jokes().stream()
                .filter(JokeService::isSafeNonSexistAndNonExplicitJoke)
                .min(Comparator.comparing(joke -> joke.joke().length()))
                .map(jokeMapper::toJokeDto)
                .orElseThrow(() -> new JokeNotFoundException("Joke not found."));
    }

    private static boolean isSafeNonSexistAndNonExplicitJoke(final Joke joke) {
        return joke.safe() && !joke.flags().sexist() && !joke.flags().explicit();
    }
}
