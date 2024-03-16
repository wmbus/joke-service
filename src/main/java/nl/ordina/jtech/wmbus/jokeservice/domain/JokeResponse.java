package nl.ordina.jtech.wmbus.jokeservice.domain;

import lombok.Builder;

import java.util.Set;

@Builder
public record JokeResponse(
        Set<Joke> jokes
) {
}
