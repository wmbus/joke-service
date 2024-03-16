package nl.ordina.jtech.wmbus.jokeservice.api;

import nl.ordina.jtech.wmbus.jokeservice.Exception.JokeNotFoundException;
import nl.ordina.jtech.wmbus.jokeservice.Exception.JokeServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JokeControllerAdvice {

    private static final String SERVICE_UNAVAILABLE_MESSAGE = "... Unfortunately, the joke-service is currently unavailable. Please contact the Joker.";
    private static final String NOT_FOUND_MESSAGE = "What happens when the rabbits run from the hat? ... Joke not found.";
    private static final String GENERAL_JOKE_ERROR_PROMPT = "Why did Porygon faint? ... %s";
    private static final String GENERAL_JOKE_ERROR_MESSAGE = "Wynaut?";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException() {
        return createErrorMessage(GENERAL_JOKE_ERROR_PROMPT.formatted(GENERAL_JOKE_ERROR_MESSAGE));
    }

    @ExceptionHandler(JokeServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleJokeServiceException(final JokeServiceException e) {
        return createErrorMessage(GENERAL_JOKE_ERROR_PROMPT.formatted(e.getMessage()));
    }

    @ExceptionHandler(JokeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleJokeNotFoundException() {
        return NOT_FOUND_MESSAGE;
    }

    private static String createErrorMessage(String message) {
        return "%s %s".formatted(message, SERVICE_UNAVAILABLE_MESSAGE);
    }
}
