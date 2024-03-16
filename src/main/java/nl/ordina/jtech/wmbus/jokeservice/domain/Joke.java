package nl.ordina.jtech.wmbus.jokeservice.domain;

import lombok.Builder;

@Builder
public record Joke(
        Long id,
        String joke,
        Boolean safe,
        Flags flags
) {
}
