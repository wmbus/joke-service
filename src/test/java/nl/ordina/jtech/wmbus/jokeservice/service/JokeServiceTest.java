package nl.ordina.jtech.wmbus.jokeservice.service;

import nl.ordina.jtech.wmbus.jokeservice.Exception.JokeNotFoundException;
import nl.ordina.jtech.wmbus.jokeservice.client.JokeApiClient;
import nl.ordina.jtech.wmbus.jokeservice.domain.Joke;
import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import nl.ordina.jtech.wmbus.jokeservice.mapper.JokeMapper;
import nl.ordina.jtech.wmbus.jokeservice.testutil.TestJokeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JokeServiceTest {

    @Mock
    private JokeApiClient jokeApiClient;
    @Mock
    private JokeMapper jokeMapper;

    @InjectMocks
    private JokeService jokeService;

    @Captor
    private ArgumentCaptor<Joke> jokeCaptor;

    @Test
    void getShortestJoke() {
        JokeDto expectedJokeDto = TestJokeFactory.getJokeDto_simple();
        when(jokeMapper.toJokeDto(jokeCaptor.capture())).thenReturn(expectedJokeDto);
        when(jokeApiClient.getJoke()).thenReturn(TestJokeFactory.getJokeResponse_simple());

        JokeDto actualJokeDto = jokeService.getShortestJoke();

        assertEquals(expectedJokeDto, actualJokeDto);

        Joke actualJoke = jokeCaptor.getValue();
        assertEquals(1L, actualJoke.id());
        assertEquals("A simple Joke that is only shorter than the long one.", actualJoke.joke());
        assertEquals(true, actualJoke.safe());
        assertEquals(false, actualJoke.flags().sexist());
        assertEquals(false, actualJoke.flags().explicit());
    }

    @Test
    void getShortestJoke_willSelectShortest() {
        JokeDto expectedJokeDto = TestJokeFactory.getJokeDto_simple();
        when(jokeMapper.toJokeDto(jokeCaptor.capture())).thenReturn(expectedJokeDto);
        when(jokeApiClient.getJoke()).thenReturn(TestJokeFactory.getJokeResponse_withLongJokes());

        JokeDto actualJokeDto = jokeService.getShortestJoke();

        assertEquals(expectedJokeDto, actualJokeDto);

        Joke actualJoke = jokeCaptor.getValue();
        assertEquals(1L, actualJoke.id());
        assertEquals("A simple Joke that is only shorter than the long one.", actualJoke.joke());
        assertEquals(true, actualJoke.safe());
        assertEquals(false, actualJoke.flags().sexist());
        assertEquals(false, actualJoke.flags().explicit());
    }

    @Test
    void getShortestJoke_willSelectSafe() {
        JokeDto expectedJokeDto = TestJokeFactory.getJokeDto_simple();
        when(jokeMapper.toJokeDto(jokeCaptor.capture())).thenReturn(expectedJokeDto);
        when(jokeApiClient.getJoke()).thenReturn(TestJokeFactory.getJokeResponse_withUnsafeJoke());

        JokeDto actualJokeDto = jokeService.getShortestJoke();

        assertEquals(expectedJokeDto, actualJokeDto);

        Joke actualJoke = jokeCaptor.getValue();
        assertEquals(1L, actualJoke.id());
        assertEquals("A simple Joke that is only shorter than the long one.", actualJoke.joke());
        assertEquals(true, actualJoke.safe());
        assertEquals(false, actualJoke.flags().sexist());
        assertEquals(false, actualJoke.flags().explicit());
    }

    @Test
    void getShortestJoke_willSelectNonSexist() {
        JokeDto expectedJokeDto = TestJokeFactory.getJokeDto_simple();
        when(jokeMapper.toJokeDto(jokeCaptor.capture())).thenReturn(expectedJokeDto);
        when(jokeApiClient.getJoke()).thenReturn(TestJokeFactory.getJokeResponse_withSexistJoke());

        JokeDto actualJokeDto = jokeService.getShortestJoke();

        assertEquals(expectedJokeDto, actualJokeDto);

        Joke actualJoke = jokeCaptor.getValue();
        assertEquals(1L, actualJoke.id());
        assertEquals("A simple Joke that is only shorter than the long one.", actualJoke.joke());
        assertEquals(true, actualJoke.safe());
        assertEquals(false, actualJoke.flags().sexist());
        assertEquals(false, actualJoke.flags().explicit());
    }

    @Test
    void getShortestJoke_willSelectNonExplicit() {
        JokeDto expectedJokeDto = TestJokeFactory.getJokeDto_simple();
        when(jokeMapper.toJokeDto(jokeCaptor.capture())).thenReturn(expectedJokeDto);
        when(jokeApiClient.getJoke()).thenReturn(TestJokeFactory.getJokeResponse_withExplicitJoke());

        JokeDto actualJokeDto = jokeService.getShortestJoke();

        assertEquals(expectedJokeDto, actualJokeDto);

        Joke actualJoke = jokeCaptor.getValue();
        assertEquals(1L, actualJoke.id());
        assertEquals("A simple Joke that is only shorter than the long one.", actualJoke.joke());
        assertEquals(true, actualJoke.safe());
        assertEquals(false, actualJoke.flags().sexist());
        assertEquals(false, actualJoke.flags().explicit());
    }

    @Test
    void getShortestJoke_noValidJokes() {
        when(jokeApiClient.getJoke()).thenReturn(TestJokeFactory.getJokeResponse_withNoValidJokes());

        JokeNotFoundException e = assertThrows(JokeNotFoundException.class, () -> jokeService.getShortestJoke());

        assertEquals("Joke not found.", e.getMessage());
    }
}