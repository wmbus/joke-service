package nl.ordina.jtech.wmbus.jokeservice.domain;

import lombok.Builder;

@Builder
public record Flags(
        Boolean sexist,
        Boolean explicit
) {
}
