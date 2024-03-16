package nl.ordina.jtech.wmbus.jokeservice.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import nl.ordina.jtech.wmbus.jokeservice.service.JokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class JokeRestController {

    private final JokeService jokeService;

    @GetMapping("/joke")
    public JokeDto getJoke() {
        log.info("Get joke...");

        JokeDto response = jokeService.getShortestJoke();

        log.info("Get joke done.");
        return response;
    }
}
