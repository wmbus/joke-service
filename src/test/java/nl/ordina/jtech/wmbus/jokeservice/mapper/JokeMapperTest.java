package nl.ordina.jtech.wmbus.jokeservice.mapper;

import nl.ordina.jtech.wmbus.jokeservice.domain.Joke;
import nl.ordina.jtech.wmbus.jokeservice.testutil.TestJokeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JokeMapperTest {

    @Autowired
    private JokeMapper jokeMapper;

    @Test
    void toJokeDto() {
        Joke expected = TestJokeFactory.getSimpleJoke();
        var actual = jokeMapper.toJokeDto(expected);

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.joke(), actual.randomJoke());
    }
}