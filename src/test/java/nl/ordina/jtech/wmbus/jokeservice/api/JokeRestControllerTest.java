package nl.ordina.jtech.wmbus.jokeservice.api;

import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import nl.ordina.jtech.wmbus.jokeservice.service.JokeService;
import nl.ordina.jtech.wmbus.jokeservice.testutil.TestJokeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JokeRestControllerTest {

    @Mock
    private JokeService jokeService;

    @InjectMocks
    private JokeRestController jokeRestController;

    @Test
    void getJoke() {
        JokeDto expected = TestJokeFactory.getJokeDto_simple();

        when(jokeService.getShortestJoke()).thenReturn(expected);

        JokeDto actual = jokeRestController.getJoke();

        assertEquals(expected, actual);
    }
}