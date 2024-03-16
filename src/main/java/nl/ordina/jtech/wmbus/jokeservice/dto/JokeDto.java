package nl.ordina.jtech.wmbus.jokeservice.dto;

import lombok.Builder;

@Builder
public record JokeDto(
        Long id,
        String randomJoke
) {
}
