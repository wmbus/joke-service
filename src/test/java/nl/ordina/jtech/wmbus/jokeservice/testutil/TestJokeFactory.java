package nl.ordina.jtech.wmbus.jokeservice.testutil;

import nl.ordina.jtech.wmbus.jokeservice.domain.Flags;
import nl.ordina.jtech.wmbus.jokeservice.domain.Joke;
import nl.ordina.jtech.wmbus.jokeservice.domain.JokeResponse;
import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;

import java.util.Set;

public final class TestJokeFactory {

    private TestJokeFactory() {
    }

    public static JokeDto getJokeDto_simple() {
        return JokeDto.builder()
                .id(1L)
                .randomJoke("Random Joke")
                .build();
    }

    public static JokeResponse getJokeResponse_simple() {
        return JokeResponse.builder()
                .jokes(Set.of(
                        getSimpleJoke()
                ))
                .build();
    }

    public static JokeResponse getJokeResponse_withLongJokes() {
        return JokeResponse.builder()
                .jokes(Set.of(
                        getLongJoke1(),
                        getSimpleJoke(),
                        getLongJoke2()
                ))
                .build();
    }

    public static JokeResponse getJokeResponse_withUnsafeJoke() {
        return JokeResponse.builder()
                .jokes(Set.of(
                        getLongJoke1(),
                        getUnsafeJoke(),
                        getSimpleJoke(),
                        getLongJoke2()
                ))
                .build();
    }

    public static JokeResponse getJokeResponse_withSexistJoke() {
        return JokeResponse.builder()
                .jokes(Set.of(
                        getLongJoke1(),
                        getSexistJoke(),
                        getSimpleJoke(),
                        getLongJoke2()
                ))
                .build();
    }

    public static JokeResponse getJokeResponse_withExplicitJoke() {
        return JokeResponse.builder()
                .jokes(Set.of(
                        getLongJoke1(),
                        getExplicitJoke(),
                        getSimpleJoke(),
                        getLongJoke2()
                ))
                .build();
    }

    public static JokeResponse getJokeResponse_withNoValidJokes() {
        return JokeResponse.builder()
                .jokes(Set.of(
                        getExplicitJoke(),
                        getSexistJoke(),
                        getUnsafeJoke()
                ))
                .build();
    }

    public static Joke getSimpleJoke() {
        return Joke.builder()
                .id(1L)
                .joke("A simple Joke that is only shorter than the long one.")
                .safe(true)
                .flags(Flags.builder()
                        .sexist(false)
                        .explicit(false)
                        .build())
                .build();
    }

    private static Joke getLongJoke1() {
        return Joke.builder()
                .id(2L)
                .joke("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .safe(true)
                .flags(Flags.builder()
                        .sexist(false)
                        .explicit(false)
                        .build())
                .build();
    }

    private static Joke getLongJoke2() {
        return Joke.builder()
                .id(3L)
                .joke("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .safe(true)
                .flags(Flags.builder()
                        .sexist(false)
                        .explicit(false)
                        .build())
                .build();
    }

    private static Joke getUnsafeJoke() {
        return Joke.builder()
                .id(4L)
                .joke("Unsafe Joke")
                .safe(false)
                .flags(Flags.builder()
                        .sexist(false)
                        .explicit(false)
                        .build())
                .build();
    }

    private static Joke getSexistJoke() {
        return Joke.builder()
                .id(5L)
                .joke("Sexist Joke")
                .safe(true)
                .flags(Flags.builder()
                        .sexist(true)
                        .explicit(false)
                        .build())
                .build();
    }

    private static Joke getExplicitJoke() {
        return Joke.builder()
                .id(6L)
                .joke("Explicit Joke")
                .safe(true)
                .flags(Flags.builder()
                        .sexist(false)
                        .explicit(true)
                        .build())
                .build();
    }
}
